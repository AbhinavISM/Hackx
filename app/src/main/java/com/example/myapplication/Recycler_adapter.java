package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Recycler_adapter extends RecyclerView.Adapter<Recycler_adapter.ViewHolder> {

    private List<BookingDetails> datalist;
    private Context context;

    public Recycler_adapter(List<BookingDetails> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public Recycler_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.mybookinglayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_adapter.ViewHolder holder, int position) {
        holder.veh_no.setText(datalist.get(position).getVehicle_number());
        holder.seat_no.setText(datalist.get(position).getSeat_number());
        holder.time.setText(datalist.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView seat_no;
        TextView veh_no;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            seat_no = itemView.findViewById(R.id.recycler_seat_number);
            veh_no = itemView.findViewById(R.id.recycler_vehicle_number);
            time = itemView.findViewById(R.id.recycler_time);
        }
    }
}
