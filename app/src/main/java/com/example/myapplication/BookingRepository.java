package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRepository {
    private List<String> occupied_list;
    private MutableLiveData<List<String>> occupied_list_live;
    private List<BookingDetails> previous_bookings_list;
    private MutableLiveData<List<BookingDetails>> previous_bookings_list_live;
    private static BookingRepository instance;
    static UnoccupiedLoadedInterface unoccupiedLoadedInterface;
    private  MutableLiveData<Boolean> booking_done;

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
    public MutableLiveData<List<BookingDetails>> get_previous_bookings(){
        previous_bookings_list = new ArrayList<>();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null) {
            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Bookings").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    previous_bookings_list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        previous_bookings_list.add(dataSnapshot.getValue(BookingDetails.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        previous_bookings_list_live = new MutableLiveData<>();
        previous_bookings_list_live.setValue(previous_bookings_list);
        return previous_bookings_list_live;
    }

    public void book_this_seat(String seat_number, String vehicle_number){
        booking_done = new MutableLiveData<>();
        booking_done.setValue(null);
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        BookingDetails bookingDetails = new BookingDetails(seat_number,vehicle_number,currentDateTimeString);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Bookings").child(currentDateTimeString).setValue(bookingDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });
        }
    }

    public MutableLiveData<Boolean> is_booking_done(){
        return booking_done;
    }

}
