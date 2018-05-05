package com.example.destroy.jsonandroidparshingbasicrecyclerview.StyleScreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.destroy.jsonandroidparshingbasicrecyclerview.Prediction.Forecastday_;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.R;

import java.util.List;


public class MaxMinAdapter extends RecyclerView.Adapter<MaxMinAdapter.MaxMinHolder>{

   private Context context;
    private List<Forecastday_>forecastday_list;

    public MaxMinAdapter(Context context, List<Forecastday_> forecastday_list) {
        this.context = context;
        this.forecastday_list = forecastday_list;
    }

    @NonNull
    @Override
    public MaxMinHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.maximum_minimum_temperature,parent,false);
        return new MaxMinHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaxMinHolder holder, int position) {
        Forecastday_ forecastday=forecastday_list.get(position);
        holder.dayone.setText(forecastday.getAvehumidity());
        holder.weather1.setText(forecastday.getConditions());
        holder.daytwo.setText(forecastday.getMaxhumidity());
        holder.weather2.setText(forecastday.getMinhumidity());
        holder.daythree.setText(forecastday.getHigh().getCelsius());
        holder.weather3.setText(forecastday.getLow().getCelsius());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MaxMinHolder extends RecyclerView.ViewHolder{
        TextView dayone,daytwo,daythree,weather1,weather2,weather3;
        public MaxMinHolder(View itemView) {
            super(itemView);
            dayone=itemView.findViewById(R.id.DayOne);
            daytwo=itemView.findViewById(R.id.Daytwo);
            daythree=itemView.findViewById(R.id.Daythree);
            weather1=itemView.findViewById(R.id.weather1);
            weather2=itemView.findViewById(R.id.weather2);
            weather3=itemView.findViewById(R.id.weather3);

        }
    }
}
