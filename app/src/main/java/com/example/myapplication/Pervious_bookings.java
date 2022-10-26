package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Pervious_bookings extends Fragment {

    TextView logout_btn;
    RecyclerView recyclerView;
    LinearLayoutManager reycler_manager;
    BookingViewModel bookingViewModel;
    Recycler_adapter recycler_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.previous_bookings, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        logout_btn = getView().findViewById(R.id.logout_text_view);
        NavController navController = Navigation.findNavController(getView());
        bookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        bookingViewModel.initBookingViewModel();
        initRecycler();
        bookingViewModel.get_previous_bookings().observe(getActivity(), new Observer<List<BookingDetails>>() {
            @Override
            public void onChanged(List<BookingDetails> bookingDetails) {
                recycler_adapter.notifyDataSetChanged();
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(),Login.class));
                getActivity().finish();
            }
        });
    }

    private void initRecycler() {
        if(getView()!=null) {
            recyclerView = getView().findViewById(R.id.recycler);
            reycler_manager = new LinearLayoutManager(getContext());
            reycler_manager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(reycler_manager);
            recycler_adapter = new Recycler_adapter(bookingViewModel.get_previous_bookings().getValue(), getContext());
            recyclerView.setAdapter(recycler_adapter);
            recycler_adapter.notifyDataSetChanged();
        }
    }

}