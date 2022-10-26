package com.example.myapplication;

public class BookingDetails {
    String seat_number;
    String vehicle_number;
    String time;

    public BookingDetails(String seat_number, String vehicle_number, String time) {
        this.seat_number = seat_number;
        this.vehicle_number = vehicle_number;
        this.time = time;
    }

    public BookingDetails(){}

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
