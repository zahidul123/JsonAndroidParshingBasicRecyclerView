package com.example.destroy.jsonandroidparshingbasicrecyclerview.StyleScreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.destroy.jsonandroidparshingbasicrecyclerview.HourlyModel.HourlyForecast;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.HourlyModel.HourlyWeather;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.R;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.model.CurrentObservation;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.model.DisplayLocation;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostviewHolder> {
  private Context context;
    private List<HourlyForecast>hweather;

    public PostAdapter(Context context, List<HourlyForecast> hweather) {
        this.context = context;
        this.hweather = hweather;
    }

    @NonNull
    @Override
    public PostviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.weatheritemshow,parent,false);

        return new PostviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostviewHolder holder, int position) {
        HourlyForecast hourlyForecast=hweather.get(position);

        holder.posttitle.setText(hourlyForecast.getCondition());

        int sunrisers=Integer.parseInt(hourlyForecast.getFCTTIME().getHour());
        if (sunrisers>12){
            sunrisers=sunrisers-12;
            holder.postdescription.setText(sunrisers+" PM");
        }else {
            holder.postdescription.setText(sunrisers+" Am");
        }


            String imagepage=hourlyForecast.getIconUrl();
            Picasso.with(context).load(imagepage).into(holder.postimage);


          holder.tempar.setText(hourlyForecast.getTemp().getMetric()+" \u2103");

    }

    @Override
    public int getItemCount() {
        return hweather.size();
    }

    public class PostviewHolder extends RecyclerView.ViewHolder{

    ImageView postimage;
    TextView posttitle,postdescription,tempar;

    public PostviewHolder(View itemView) {
        super(itemView);
        postimage=itemView.findViewById(R.id.postimage);
        posttitle=itemView.findViewById(R.id.postTitle);
        postdescription=itemView.findViewById(R.id.postdiscriotion);
        tempar=itemView.findViewById(R.id.temperatures);
    }
}
}
