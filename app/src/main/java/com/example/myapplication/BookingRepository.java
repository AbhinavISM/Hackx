package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRepository {
    private List<String> occupied_list;
    private MutableLiveData<List<String>> occupied_list_live;
    private static BookingRepository instance;
    static UnoccupiedLoadedInterface unoccupiedLoadedInterface;

    public static BookingRepository getInstance(UnoccupiedLoadedInterface context){
        unoccupiedLoadedInterface = context;
        if(instance==null){
            instance =  new BookingRepository();
        }
        return instance;
    }

    public MutableLiveData<List<String>> get_occupied_list_live(){
        occupied_list = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Booking_data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                occupied_list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    occupied_list.add(dataSnapshot.getValue().toString());
                }
                unoccupiedLoadedInterface.onUnoccupiedLoaded(occupied_list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        occupied_list_live = new MutableLiveData<>();
        occupied_list_live.setValue(occupied_list);
        return occupied_list_live;
    }

    public void book_this_seat(String seat_index, String vehicle_number){
    }

}
