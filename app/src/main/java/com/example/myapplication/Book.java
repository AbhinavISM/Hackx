package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book extends Fragment {
    //all seats
    ImageButton seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8, seat9, seat10, seat11, seat12, seat13, seat14, seat15;
    //nav controller
    NavController navController;
    //view model
    BookingViewModel bookingViewModel;
    //list of unavailable seats
    List<String> unavailable_seats;
    //to discard previous selection
    View previous_selected_view = null;
    //button for booking a seat
    Button book;
    //textview to enter vehicle number
    EditText vehicle_edit_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.book, container, false);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        //find nav controller
        navController = Navigation.findNavController(getView());
        //find view model
        bookingViewModel = new ViewModelProvider(this).get(BookingViewModel.class);
        bookingViewModel.initBookingViewModel();
        //find button for booking all the seats
        book = getView().findViewById(R.id.book_button);
        //find edit text for vehicle number
        vehicle_edit_text = getView().findViewById(R.id.vehicle_edit_text);
        //find all seats
        seat1 = getView().findViewById(R.id.seat1);
        seat2 = getView().findViewById(R.id.seat2);
        seat3 = getView().findViewById(R.id.seat3);
        seat4 = getView().findViewById(R.id.seat4);
        seat5 = getView().findViewById(R.id.seat5);
        seat6 = getView().findViewById(R.id.seat6);
        seat7 = getView().findViewById(R.id.seat7);
        seat8 = getView().findViewById(R.id.seat8);
        seat9 = getView().findViewById(R.id.seat9);
        seat10 = getView().findViewById(R.id.seat10);
        seat11 = getView().findViewById(R.id.seat11);
        seat12 = getView().findViewById(R.id.seat12);
        seat13 = getView().findViewById(R.id.seat13);
        seat14 = getView().findViewById(R.id.seat14);
        seat15 = getView().findViewById(R.id.seat15);
        //set all seats to available
        seat1.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat2.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat3.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat4.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat5.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat6.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat7.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat8.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat9.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat10.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat11.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat12.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat13.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat14.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        seat15.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
        //observe unavailable seats
        bookingViewModel.get_occupied_list_live().observe(getActivity(), new Observer<List<String>>() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onChanged(List<String> strings) {
                unavailable_seats = strings;
                seat1.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat2.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat3.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat4.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat5.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat6.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat7.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat8.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat9.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat10.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat11.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat12.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat13.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat14.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                seat15.setBackground(getContext().getApplicationContext().getResources().getDrawable(R.drawable.rounded_square_green_2_png));
                for (String seat : strings){
                    switch (seat){
                        case "1" : seat1.setBackground(
                                getContext()
                                .getApplicationContext()
                                .getResources()
                                .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat1.setEnabled(false);
                            break;

                        case "2" : seat2.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat2.setEnabled(false);
                            break;

                        case "3" : seat3.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat3.setEnabled(false);
                            break;

                        case "4" : seat4.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat4.setEnabled(false);
                            break;

                        case "5" : seat5.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat5.setEnabled(false);
                            break;

                        case "6" : seat6.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat6.setEnabled(false);
                            break;

                        case "7" : seat7.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat7.setEnabled(false);
                            break;

                        case "8" : seat8.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat8.setEnabled(false);
                            break;

                        case "9" : seat9.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat9.setEnabled(false);
                            break;

                        case "10" : seat10.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat10.setEnabled(false);
                            break;

                        case "11" : seat11.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat11.setEnabled(false);
                            break;

                        case "12" : seat12.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat12.setEnabled(false);
                            break;

                        case "13" : seat13.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat13.setEnabled(false);
                            break;

                        case "14" : seat14.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat14.setEnabled(false);
                            break;

                        case "15" : seat15.setBackground(
                                getContext()
                                        .getApplicationContext()
                                        .getResources()
                                        .getDrawable(R.drawable.rounded_square_dumb_png));
                            seat15.setEnabled(false);
                            break;
                    }
                }
            }
        });
        //define a click listener for all seats
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(previous_selected_view!=null){
                    previous_selected_view.setBackground(getContext()
                            .getApplicationContext()
                            .getResources()
                            .getDrawable(R.drawable.rounded_square_green_2_png));
                    previous_selected_view.setSelected(false);
                }
                view.setSelected(true);
                view.setBackground(getContext()
                        .getApplicationContext()
                        .getResources()
                        .getDrawable(R.drawable.rounded_square_2_png));
                previous_selected_view = view;
//                Map<String, Object> map = new HashMap<>();
//                map.put(String.valueOf(0),3);
//                map.put(String.valueOf(1),4);
//                FirebaseDatabase.getInstance().getReference("Booking_data").setValue(map);
            }
        };
        //set on click listener to all seats
        seat1.setOnClickListener(onClickListener);
        seat2.setOnClickListener(onClickListener);
        seat3.setOnClickListener(onClickListener);
        seat4.setOnClickListener(onClickListener);
        seat5.setOnClickListener(onClickListener);
        seat6.setOnClickListener(onClickListener);
        seat7.setOnClickListener(onClickListener);
        seat8.setOnClickListener(onClickListener);
        seat9.setOnClickListener(onClickListener);
        seat10.setOnClickListener(onClickListener);
        seat11.setOnClickListener(onClickListener);
        seat12.setOnClickListener(onClickListener);
        seat13.setOnClickListener(onClickListener);
        seat14.setOnClickListener(onClickListener);
        seat15.setOnClickListener(onClickListener);

        //define a click listener for book button
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vehicle_edit_text.getText().toString().length()==0){
                    Toast.makeText(getContext(), "enter vehicle number", Toast.LENGTH_SHORT).show();
                }else if(previous_selected_view!=null){
                    bookingViewModel.book_this_seat(previous_selected_view.getTooltipText().toString(),vehicle_edit_text.getText().toString());
                }
                else{
                    Toast.makeText(getContext(), "please select a slot", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        bookingViewModel.is_booking_done().observe(getActivity(), new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                if(aBoolean == null){
//
//                }
//                else if (!aBoolean){
//                    Toast.makeText(getContext(), "booking failed", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(getContext(), "booking successful", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

}