package com.example.destroy.jsonandroidparshingbasicrecyclerview.AllActivity;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.destroy.jsonandroidparshingbasicrecyclerview.ApiConfigure.RetrofitClient;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.ApiConfigure.WeatherApi;

import com.example.destroy.jsonandroidparshingbasicrecyclerview.Prediction.PredictionList;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.R;

import com.example.destroy.jsonandroidparshingbasicrecyclerview.TenDaysForcast.SevendaysHistory;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;


import java.util.ArrayList;


public class GraphViewShow extends AppCompatActivity {

    WeatherApi weatherApig;
    TextView day1,day2,day3;
    TextView temp1,temp2,temp3;
    TextView cond1,cond2,cond3;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view_show);
        String cityin= getIntent().getStringExtra("city");
        day1=findViewById(R.id.day1);
        day2=findViewById(R.id.day2);
        day3=findViewById(R.id.day3);

        temp1=findViewById(R.id.temp1);
        temp2=findViewById(R.id.temp2);
        temp3=findViewById(R.id.temp3);

        cond1=findViewById(R.id.cond1);
        cond2=findViewById(R.id.cond2);
        cond3=findViewById(R.id.cond3);

        barChart =findViewById(R.id.barshow);

        weatherApig=RetrofitClient.getRetrofitClient().create(WeatherApi.class);
        Call<PredictionList> predictionListCall=weatherApig.getPredictionList();
        Call<SevendaysHistory>sevendaysHistoryCall=weatherApig.getSevendaysHistory();
        predictionListCall.enqueue(new Callback<PredictionList>() {
            @Override
            public void onResponse(Call<PredictionList> call, Response<PredictionList> response) {
                PredictionList predictionList=response.body();
                day1.setText(predictionList.getForecast().getSimpleforecast().getForecastday().get(1).getDate().getWeekday());
                temp1.setText(predictionList.getForecast().getSimpleforecast().getForecastday().get(1).getHigh().getCelsius());
                cond1.setText(predictionList.getForecast().getSimpleforecast().getForecastday().get(1).getConditions());

                day2.setText(predictionList.getForecast().getSimpleforecast().getForecastday().get(2).getDate().getWeekday());
                temp2.setText(predictionList.getForecast().getSimpleforecast().getForecastday().get(2).getHigh().getCelsius());
                cond2.setText(predictionList.getForecast().getSimpleforecast().getForecastday().get(2).getConditions());

                day3.setText(predictionList.getForecast().getSimpleforecast().getForecastday().get(3).getDate().getWeekday());
                temp3.setText(predictionList.getForecast().getSimpleforecast().getForecastday().get(3).getHigh().getCelsius());
                cond3.setText(predictionList.getForecast().getSimpleforecast().getForecastday().get(3).getConditions());

            }

            @Override
            public void onFailure(Call<PredictionList> call, Throwable t) {

            }
        });


    sevendaysHistoryCall.enqueue(new Callback<SevendaysHistory>() {
        @Override
        public void onResponse(Call<SevendaysHistory> call, Response<SevendaysHistory> response) {
            SevendaysHistory sevendaysHistory=response.body();
            int day11=Integer.parseInt(sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getCelsius());
            int day12=Integer.parseInt(sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(1).getHigh().getCelsius());
            int day13=Integer.parseInt(sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(2).getHigh().getCelsius());
            int day14=Integer.parseInt(sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(3).getHigh().getCelsius());
            int day15=Integer.parseInt(sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(4).getHigh().getCelsius());
            int day16=Integer.parseInt(sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(5).getHigh().getCelsius());
            int day17=Integer.parseInt(sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(6).getHigh().getCelsius());

            ArrayList<BarEntry>barEntries=new ArrayList<>();
            barEntries.add(new BarEntry(day11,0));
            barEntries.add(new BarEntry(day12,1));
            barEntries.add(new BarEntry(day13,2));
            barEntries.add(new BarEntry(day14,3));
            barEntries.add(new BarEntry(day15,4));
            barEntries.add(new BarEntry(day16,5));
            barEntries.add(new BarEntry(day17,6));

            BarDataSet barDataSet=new BarDataSet(barEntries,"All Datas");

            String daynm1=sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(0).getDate().getWeekday();
            String daynm2=sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(1).getDate().getWeekday();
            String daynm3=sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(2).getDate().getWeekday();
            String daynm4=sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(3).getDate().getWeekday();
            String daynm5=sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(4).getDate().getWeekday();
            String daynm6=sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(5).getDate().getWeekday();
            String daynm7=sevendaysHistory.getForecast().getSimpleforecast().getForecastday().get(6).getDate().getWeekday();

            ArrayList<String>barxday=new ArrayList<>();
            barxday.add(daynm1);
            barxday.add(daynm2);
            barxday.add(daynm3);
            barxday.add(daynm4);
            barxday.add(daynm5);
            barxday.add(daynm6);
            barxday.add(daynm7);

            BarData barData=new BarData(barxday,barDataSet);
            barData.setValueTextSize(13f);
            barData.setValueTextColor(Color.DKGRAY);
            barChart.setData(barData);
            barChart.invalidate();




        }

        @Override
        public void onFailure(Call<SevendaysHistory> call, Throwable t) {

        }
    });

    }
}
