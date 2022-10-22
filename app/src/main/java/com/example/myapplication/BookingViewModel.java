package com.example.myapplication;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class BookingViewModel extends ViewModel implements UnoccupiedLoadedInterface {

    private MutableLiveData<List<String>> occupied_list_live;
    private BookingRepository bookingRepository;

    public LiveData<List<String>> get_occupied_list_live(){
        return occupied_list_live;
    }

    public void initBookingViewModel() {
        if (occupied_list_live == null) {
            bookingRepository = BookingRepository.getInstance(this);
            occupied_list_live = bookingRepository.get_occupied_list_live();
        }
    }

    public void book_this_seat(String seat_index, String vehicle_number){
        Log.d("i", "ye book hua : "+ seat_index);
        bookingRepository.book_this_seat(seat_index,vehicle_number);
    }
    @Override
    public void onUnoccupiedLoaded(List<String> fully_loaded_data) {
        occupied_list_live.setValue(fully_loaded_data);
    }

}
