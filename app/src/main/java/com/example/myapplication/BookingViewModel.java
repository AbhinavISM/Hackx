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
    private MutableLiveData<Boolean> booking_done;
    private MutableLiveData <List<BookingDetails>> previous_bookings_list;

    public LiveData<List<String>> get_occupied_list_live(){
        return occupied_list_live;
    }

    public void initBookingViewModel() {
        if (occupied_list_live == null) {
            bookingRepository = BookingRepository.getInstance(this);
            occupied_list_live = bookingRepository.get_occupied_list_live();
            booking_done = bookingRepository.is_booking_done();
            previous_bookings_list = bookingRepository.get_previous_bookings();
        }
    }

    public void book_this_seat(String seat_number, String vehicle_number){
        Log.d("i", "ye book hua : "+ seat_number);
        bookingRepository.book_this_seat(seat_number,vehicle_number);
    }
    @Override
    public void onUnoccupiedLoaded(List<String> fully_loaded_data) {
        occupied_list_live.setValue(fully_loaded_data);
    }

    public LiveData<Boolean> is_booking_done(){
        return booking_done;
    }

    public LiveData<List<BookingDetails>> get_previous_bookings(){
        return previous_bookings_list;
    }
}
