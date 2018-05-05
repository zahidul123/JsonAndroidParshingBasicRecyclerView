package com.example.destroy.jsonandroidparshingbasicrecyclerview.AllActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.destroy.jsonandroidparshingbasicrecyclerview.ApiConfigure.RetrofitClient;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.ApiConfigure.WeatherApi;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.HourlyModel.HourlyWeather;

import com.example.destroy.jsonandroidparshingbasicrecyclerview.Prediction.PredictionList;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.R;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.StyleScreen.PostAdapter;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.SunriseSunSetModel.AstronomyOfDay;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.model.WeatherList;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static TextView tempersture,place;
    public static ImageView image;
    private EditText editText;
    WeatherApi weatherApi;
    RecyclerView recyclerView;
    public static TextView sunsines,sunrises,currenttimes;
    private ImageView imageView;
    private String city="dhaka";
    Button submit;
    TextView maxtem,mintem,windsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.cityinptrf);
        image=findViewById(R.id.imageView);
        tempersture=findViewById(R.id.textView);
        place=findViewById(R.id.textView2);
        recyclerView=findViewById(R.id.postlist);


        sunsines=findViewById(R.id.sunsine);
        sunrises=findViewById(R.id.sunrise);
        currenttimes=findViewById(R.id.currenttime);
        imageView=findViewById(R.id.imageView2);
        submit=findViewById(R.id.cityselect);


        maxtem=findViewById(R.id.maxtem);
        mintem=findViewById(R.id.mintem);
        windsp=findViewById(R.id.windspeed);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm a");
        String formattedDate = df.format(calendar.getTime());
        currenttimes.setText(formattedDate);

        weatherApi= RetrofitClient.getRetrofitClient().create(WeatherApi.class);
        Call<WeatherList>weatherListCalldef=weatherApi.getWeather();
        Call<HourlyWeather>hourlyweahterdef=weatherApi.getHourlyWeather();
        Call<AstronomyOfDay>astronomyofdef=weatherApi.getAstronomyofDay();
        final Call<PredictionList>predictionListCalldef=weatherApi.getPredictionList();

        weatherListCalldef.enqueue(new Callback<WeatherList>() {
            @Override
            public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                WeatherList weatherList=response.body();
                String imageurl=weatherList.getCurrentObservation().getIconUrl();
                Picasso.with(MainActivity.this).load(imageurl).into(image);
                tempersture.setText(weatherList.getCurrentObservation().getTemperatureString());
                place.setText(weatherList.getCurrentObservation().getDisplayLocation().getCity());

            }

            @Override
            public void onFailure(Call<WeatherList> call, Throwable t) {

            }
        });

        hourlyweahterdef.enqueue(new Callback<HourlyWeather>() {
            @Override
            public void onResponse(Call<HourlyWeather> call, Response<HourlyWeather> response) {
                HourlyWeather weatherinstance=response.body();
                recyclerView.setAdapter(new PostAdapter(MainActivity.this,weatherinstance.getHourlyForecast()));

            }

            @Override
            public void onFailure(Call<HourlyWeather> call, Throwable t) {

            }
        });

        astronomyofdef.enqueue(new Callback<AstronomyOfDay>() {
            @Override
            public void onResponse(Call<AstronomyOfDay> call, Response<AstronomyOfDay> response) {
                AstronomyOfDay astronofday=response.body();

                int sunrisers=Integer.parseInt(astronofday.getSunPhase().getSunrise().getHour());
                if (sunrisers>12){
                    sunrisers=sunrisers-12;
                    sunrises.setText(sunrisers+" PM");
                }else {
                    sunrises.setText(sunrisers+" Am");
                }
                int sunsine=Integer.parseInt(astronofday.getSunPhase().getSunset().getHour());
                if (sunsine>12){
                    sunsine=sunsine-12;
                    sunsines.setText(sunsine+" PM");
                }else{
                    sunsines.setText(sunsine+" AM");
                }




            }

            @Override
            public void onFailure(Call<AstronomyOfDay> call, Throwable t) {

            }
        });

        predictionListCalldef.enqueue(new Callback<PredictionList>() {
            @Override
            public void onResponse(Call<PredictionList> call, Response<PredictionList> response) {
                PredictionList predictionList=response.body();
                maxtem.setText("Max Temp :"+predictionList.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getCelsius()+" \u2103");
                mintem.setText("Min Temp :"+predictionList.getForecast().getSimpleforecast().getForecastday().get(0).getLow().getCelsius()+" \u2103");
                windsp.setText("Wind Speed : "+predictionList.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getKph()+"Kph");
            }

            @Override
            public void onFailure(Call<PredictionList> call, Throwable t) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city=editText.getText().toString().trim();
                Toast.makeText(MainActivity.this, "submit is clicked "+city, Toast.LENGTH_SHORT).show();
                if (city.toLowerCase().equals("dhaka")){
                    Call<WeatherList>weatherListCall=weatherApi.getWeather();
                    Call<HourlyWeather>hourlyweahter=weatherApi.getHourlyWeather();
                    Call<AstronomyOfDay>astronomyof=weatherApi.getAstronomyofDay();
                    Call<PredictionList>predictionListCalldk=weatherApi.getPredictionList();

                    predictionListCalldk.enqueue(new Callback<PredictionList>() {
                        @Override
                        public void onResponse(Call<PredictionList> call, Response<PredictionList> response) {
                            PredictionList predictionLists=response.body();
                            maxtem.setText("Max Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getCelsius()+" \u2103");
                            mintem.setText("Min Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getLow().getCelsius()+" \u2103");
                            windsp.setText("Wind Speed : "+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getKph()+"Kph");
                        }

                        @Override
                        public void onFailure(Call<PredictionList> call, Throwable t) {

                        }
                    });


                    weatherListCall.enqueue(new Callback<WeatherList>() {
                        @Override
                        public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                            WeatherList weatherList=response.body();
                            String imageurl=weatherList.getCurrentObservation().getIconUrl();
                            Picasso.with(MainActivity.this).load(imageurl).into(image);
                            tempersture.setText(weatherList.getCurrentObservation().getTemperatureString());
                            place.setText(weatherList.getCurrentObservation().getDisplayLocation().getCity());

                        }

                        @Override
                        public void onFailure(Call<WeatherList> call, Throwable t) {

                        }
                    });

                    hourlyweahter.enqueue(new Callback<HourlyWeather>() {
                        @Override
                        public void onResponse(Call<HourlyWeather> call, Response<HourlyWeather> response) {
                            HourlyWeather weatherinstance=response.body();
                            recyclerView.setAdapter(new PostAdapter(MainActivity.this,weatherinstance.getHourlyForecast()));

                        }

                        @Override
                        public void onFailure(Call<HourlyWeather> call, Throwable t) {

                        }
                    });

                    astronomyof.enqueue(new Callback<AstronomyOfDay>() {
                        @Override
                        public void onResponse(Call<AstronomyOfDay> call, Response<AstronomyOfDay> response) {
                            AstronomyOfDay astronofday=response.body();

                            int sunrisers=Integer.parseInt(astronofday.getSunPhase().getSunrise().getHour());
                            if (sunrisers>12){
                                sunrisers=sunrisers-12;
                                sunrises.setText(sunrisers+" PM");
                            }else {
                                sunrises.setText(sunrisers+" Am");
                            }
                            int sunsine=Integer.parseInt(astronofday.getSunPhase().getSunset().getHour());
                            if (sunsine>12){
                              sunsine=sunsine-12;
                                sunsines.setText(sunsine+" PM");
                            }else{
                                sunsines.setText(sunsine+" AM");
                            }



                        }

                        @Override
                        public void onFailure(Call<AstronomyOfDay> call, Throwable t) {

                        }
                    });
                }

                if (city.toLowerCase().equals("rajshahi")){
                    Call<WeatherList>weatherListCall=weatherApi.getWeatherraj();
                    Call<HourlyWeather>hourlyweahter=weatherApi.getHourlyWeatherraj();
                    Call<AstronomyOfDay>astronomyof=weatherApi.getAstronomyofDayraj();

                    Call<PredictionList>predictionListCalldk=weatherApi.getPredictionListraj();

                    predictionListCalldk.enqueue(new Callback<PredictionList>() {
                        @Override
                        public void onResponse(Call<PredictionList> call, Response<PredictionList> response) {
                            PredictionList predictionLists=response.body();
                            maxtem.setText("Max Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getCelsius()+" \u2103");
                            mintem.setText("Min Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getLow().getCelsius()+" \u2103");
                            windsp.setText("Wind Speed : "+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getKph()+"Kph");
                        }

                        @Override
                        public void onFailure(Call<PredictionList> call, Throwable t) {

                        }
                    });
                    weatherListCall.enqueue(new Callback<WeatherList>() {
                        @Override
                        public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                            WeatherList weatherList=response.body();

                            String imageurl=weatherList.getCurrentObservation().getIconUrl();
                            Picasso.with(MainActivity.this).load(imageurl).into(image);
                            tempersture.setText(weatherList.getCurrentObservation().getTemperatureString());
                            place.setText(weatherList.getCurrentObservation().getDisplayLocation().getCity());

                        }

                        @Override
                        public void onFailure(Call<WeatherList> call, Throwable t) {

                        }
                    });

                    hourlyweahter.enqueue(new Callback<HourlyWeather>() {
                        @Override
                        public void onResponse(Call<HourlyWeather> call, Response<HourlyWeather> response) {
                            HourlyWeather weatherinstance=response.body();
                            recyclerView.setAdapter(new PostAdapter(MainActivity.this,weatherinstance.getHourlyForecast()));

                        }

                        @Override
                        public void onFailure(Call<HourlyWeather> call, Throwable t) {

                        }
                    });

                    astronomyof.enqueue(new Callback<AstronomyOfDay>() {
                        @Override
                        public void onResponse(Call<AstronomyOfDay> call, Response<AstronomyOfDay> response) {
                            AstronomyOfDay astronofday=response.body();

                            int sunrisers=Integer.parseInt(astronofday.getSunPhase().getSunrise().getHour());
                            if (sunrisers>12){
                                sunrisers=sunrisers-12;
                                sunrises.setText(sunrisers+" PM");
                            }else {
                                sunrises.setText(sunrisers+" Am");
                            }
                            int sunsine=Integer.parseInt(astronofday.getSunPhase().getSunset().getHour());
                            if (sunsine>12){
                                sunsine=sunsine-12;
                                sunsines.setText(sunsine+" PM");
                            }else{
                                sunsines.setText(sunsine+" AM");
                            }



                        }

                        @Override
                        public void onFailure(Call<AstronomyOfDay> call, Throwable t) {

                        }
                    });
                }


                if (city.toLowerCase().equals("khulna")){
                    Call<WeatherList>weatherListCall=weatherApi.getWeatherkhul();
                    Call<HourlyWeather>hourlyweahter=weatherApi.getHourlyWeatherkhul();
                    Call<AstronomyOfDay>astronomyof=weatherApi.getAstronomyofDaykhul();
                    Call<PredictionList>predictionListCalldk=weatherApi.getPredictionListkhul();

                    predictionListCalldk.enqueue(new Callback<PredictionList>() {
                        @Override
                        public void onResponse(Call<PredictionList> call, Response<PredictionList> response) {
                            PredictionList predictionLists=response.body();
                            maxtem.setText("Max Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getCelsius()+" \u2103");
                            mintem.setText("Min Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getLow().getCelsius()+" \u2103");
                            windsp.setText("Wind Speed : "+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getKph()+"Kph");
                        }

                        @Override
                        public void onFailure(Call<PredictionList> call, Throwable t) {

                        }
                    });

                    weatherListCall.enqueue(new Callback<WeatherList>() {
                        @Override
                        public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                            WeatherList weatherList=response.body();
                            String imageurl=weatherList.getCurrentObservation().getIconUrl();
                            Picasso.with(MainActivity.this).load(imageurl).into(image);
                            tempersture.setText(weatherList.getCurrentObservation().getTemperatureString());
                            place.setText(weatherList.getCurrentObservation().getDisplayLocation().getCity());

                        }

                        @Override
                        public void onFailure(Call<WeatherList> call, Throwable t) {

                        }
                    });

                    hourlyweahter.enqueue(new Callback<HourlyWeather>() {
                        @Override
                        public void onResponse(Call<HourlyWeather> call, Response<HourlyWeather> response) {
                            HourlyWeather weatherinstance=response.body();
                            recyclerView.setAdapter(new PostAdapter(MainActivity.this,weatherinstance.getHourlyForecast()));

                        }

                        @Override
                        public void onFailure(Call<HourlyWeather> call, Throwable t) {

                        }
                    });

                    astronomyof.enqueue(new Callback<AstronomyOfDay>() {
                        @Override
                        public void onResponse(Call<AstronomyOfDay> call, Response<AstronomyOfDay> response) {
                            AstronomyOfDay astronofday=response.body();
                            int sunrisers=Integer.parseInt(astronofday.getSunPhase().getSunrise().getHour());
                            if (sunrisers>12){
                                sunrisers=sunrisers-12;
                                sunrises.setText(sunrisers+" PM");
                            }else {
                                sunrises.setText(sunrisers+" Am");
                            }
                            int sunsine=Integer.parseInt(astronofday.getSunPhase().getSunset().getHour());
                            if (sunsine>12){
                                sunsine=sunsine-12;
                                sunsines.setText(sunsine+" PM");
                            }else{
                                sunsines.setText(sunsine+" AM");
                            }




                        }

                        @Override
                        public void onFailure(Call<AstronomyOfDay> call, Throwable t) {

                        }
                    });
                }


                if (city.toLowerCase().equals("chittagong")){
                    Call<WeatherList>weatherListCall=weatherApi.getWeatherchit();
                    Call<HourlyWeather>hourlyweahter=weatherApi.getHourlyWeatherchit();
                    Call<AstronomyOfDay>astronomyof=weatherApi.getAstronomyofDaychit();

                    Call<PredictionList>predictionListCalldk=weatherApi.getPredictionListchit();

                    predictionListCalldk.enqueue(new Callback<PredictionList>() {
                        @Override
                        public void onResponse(Call<PredictionList> call, Response<PredictionList> response) {
                            PredictionList predictionLists=response.body();
                            maxtem.setText("Max Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getCelsius()+" \u2103");
                            mintem.setText("Min Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getLow().getCelsius()+" \u2103");
                            windsp.setText("Wind Speed : "+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getKph()+"Kph");
                        }

                        @Override
                        public void onFailure(Call<PredictionList> call, Throwable t) {

                        }
                    });
                    weatherListCall.enqueue(new Callback<WeatherList>() {
                        @Override
                        public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                            WeatherList weatherList=response.body();
                            String imageurl=weatherList.getCurrentObservation().getIconUrl();
                            Picasso.with(MainActivity.this).load(imageurl).into(image);
                            tempersture.setText(weatherList.getCurrentObservation().getTemperatureString());
                            place.setText(weatherList.getCurrentObservation().getDisplayLocation().getCity());

                        }

                        @Override
                        public void onFailure(Call<WeatherList> call, Throwable t) {

                        }
                    });

                    hourlyweahter.enqueue(new Callback<HourlyWeather>() {
                        @Override
                        public void onResponse(Call<HourlyWeather> call, Response<HourlyWeather> response) {
                            HourlyWeather weatherinstance=response.body();
                            recyclerView.setAdapter(new PostAdapter(MainActivity.this,weatherinstance.getHourlyForecast()));

                        }

                        @Override
                        public void onFailure(Call<HourlyWeather> call, Throwable t) {

                        }
                    });

                    astronomyof.enqueue(new Callback<AstronomyOfDay>() {
                        @Override
                        public void onResponse(Call<AstronomyOfDay> call, Response<AstronomyOfDay> response) {
                            AstronomyOfDay astronofday=response.body();
                            int sunrisers=Integer.parseInt(astronofday.getSunPhase().getSunrise().getHour());
                            if (sunrisers>12){
                                sunrisers=sunrisers-12;
                                sunrises.setText(sunrisers+" PM");
                            }else {
                                sunrises.setText(sunrisers+" Am");
                            }
                            int sunsine=Integer.parseInt(astronofday.getSunPhase().getSunset().getHour());
                            if (sunsine>12){
                                sunsine=sunsine-12;
                                sunsines.setText(sunsine+" PM");
                            }else{
                                sunsines.setText(sunsine+" AM");
                            }




                        }

                        @Override
                        public void onFailure(Call<AstronomyOfDay> call, Throwable t) {

                        }
                    });
                }

                if (city.toLowerCase().equals("barisal")){
                    Call<WeatherList>weatherListCall=weatherApi.getWeather();
                    Call<HourlyWeather>hourlyweahter=weatherApi.getHourlyWeather();
                    Call<AstronomyOfDay>astronomyof=weatherApi.getAstronomyofDay();

                    Call<PredictionList>predictionListCalldk=weatherApi.getPredictionList();

                    predictionListCalldk.enqueue(new Callback<PredictionList>() {
                        @Override
                        public void onResponse(Call<PredictionList> call, Response<PredictionList> response) {
                            PredictionList predictionLists=response.body();
                            maxtem.setText("Max Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getCelsius()+" \u2103");
                            mintem.setText("Min Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getLow().getCelsius()+" \u2103");
                            windsp.setText("Wind Speed : "+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getKph()+"Kph");
                        }

                        @Override
                        public void onFailure(Call<PredictionList> call, Throwable t) {

                        }
                    });

                    weatherListCall.enqueue(new Callback<WeatherList>() {
                        @Override
                        public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                            WeatherList weatherList=response.body();
                            String imageurl=weatherList.getCurrentObservation().getIconUrl();
                            Picasso.with(MainActivity.this).load(imageurl).into(image);
                            tempersture.setText(weatherList.getCurrentObservation().getTemperatureString());
                            place.setText("Barisal");

                        }

                        @Override
                        public void onFailure(Call<WeatherList> call, Throwable t) {

                        }
                    });

                    hourlyweahter.enqueue(new Callback<HourlyWeather>() {
                        @Override
                        public void onResponse(Call<HourlyWeather> call, Response<HourlyWeather> response) {
                            HourlyWeather weatherinstance=response.body();
                            recyclerView.setAdapter(new PostAdapter(MainActivity.this,weatherinstance.getHourlyForecast()));

                        }

                        @Override
                        public void onFailure(Call<HourlyWeather> call, Throwable t) {

                        }
                    });

                    astronomyof.enqueue(new Callback<AstronomyOfDay>() {
                        @Override
                        public void onResponse(Call<AstronomyOfDay> call, Response<AstronomyOfDay> response) {
                            AstronomyOfDay astronofday=response.body();
                            int sunrisers=Integer.parseInt(astronofday.getSunPhase().getSunrise().getHour());
                            if (sunrisers>12){
                                sunrisers=sunrisers-12;
                                sunrises.setText(sunrisers+" PM");
                            }else {
                                sunrises.setText(sunrisers+" Am");
                            }
                            int sunsine=Integer.parseInt(astronofday.getSunPhase().getSunset().getHour());
                            if (sunsine>12){
                                sunsine=sunsine-12;
                                sunsines.setText(sunsine+" PM");
                            }else{
                                sunsines.setText(sunsine+" AM");
                            }


                            Calendar calendar=Calendar.getInstance();
                            SimpleDateFormat df = new SimpleDateFormat("HH:mm a");
                            String formattedDate = df.format(calendar.getTime());
                            currenttimes.setText(formattedDate);

                        }

                        @Override
                        public void onFailure(Call<AstronomyOfDay> call, Throwable t) {

                        }
                    });
                }
                if (city.toLowerCase().equals("rangpur")){
                    Call<WeatherList>weatherListCall=weatherApi.getWeatherraj();
                    Call<HourlyWeather>hourlyweahter=weatherApi.getHourlyWeatherraj();
                    Call<AstronomyOfDay>astronomyof=weatherApi.getAstronomyofDayraj();
                    Call<PredictionList>predictionListCalldk=weatherApi.getPredictionListraj();

                    predictionListCalldk.enqueue(new Callback<PredictionList>() {
                        @Override
                        public void onResponse(Call<PredictionList> call, Response<PredictionList> response) {
                            PredictionList predictionLists=response.body();
                            maxtem.setText("Max Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getCelsius()+" \u2103");
                            mintem.setText("Min Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getLow().getCelsius()+" \u2103");
                            windsp.setText("Wind Speed : "+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getKph()+"Kph");
                        }

                        @Override
                        public void onFailure(Call<PredictionList> call, Throwable t) {

                        }
                    });

                    weatherListCall.enqueue(new Callback<WeatherList>() {
                        @Override
                        public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                            WeatherList weatherList=response.body();
                            String imageurl=weatherList.getCurrentObservation().getIconUrl();
                            Picasso.with(MainActivity.this).load(imageurl).into(image);
                            tempersture.setText(weatherList.getCurrentObservation().getTemperatureString());
                            place.setText("Rangpur");

                        }

                        @Override
                        public void onFailure(Call<WeatherList> call, Throwable t) {

                        }
                    });

                    hourlyweahter.enqueue(new Callback<HourlyWeather>() {
                        @Override
                        public void onResponse(Call<HourlyWeather> call, Response<HourlyWeather> response) {
                            HourlyWeather weatherinstance=response.body();
                            recyclerView.setAdapter(new PostAdapter(MainActivity.this,weatherinstance.getHourlyForecast()));

                        }

                        @Override
                        public void onFailure(Call<HourlyWeather> call, Throwable t) {

                        }
                    });

                    astronomyof.enqueue(new Callback<AstronomyOfDay>() {
                        @Override
                        public void onResponse(Call<AstronomyOfDay> call, Response<AstronomyOfDay> response) {
                            AstronomyOfDay astronofday=response.body();

                            int sunrisers=Integer.parseInt(astronofday.getSunPhase().getSunrise().getHour());
                            if (sunrisers>12){
                                sunrisers=sunrisers-12;
                                sunrises.setText(sunrisers+" PM");
                            }else {
                                sunrises.setText(sunrisers+" Am");
                            }
                            int sunsine=Integer.parseInt(astronofday.getSunPhase().getSunset().getHour());
                            if (sunsine>12){
                                sunsine=sunsine-12;
                                sunsines.setText(sunsine+" PM");
                            }else{
                                sunsines.setText(sunsine+" AM");
                            }



                        }

                        @Override
                        public void onFailure(Call<AstronomyOfDay> call, Throwable t) {

                        }
                    });
                }

                if (city.toLowerCase().equals("sylhet")){
                    Call<WeatherList>weatherListCall=weatherApi.getWeathersyl();
                    Call<HourlyWeather>hourlyweahter=weatherApi.getHourlyWeathersyl();
                    Call<AstronomyOfDay>astronomyof=weatherApi.getAstronomyofDaysyl();
                    Call<PredictionList>predictionListCalldk=weatherApi.getPredictionListsyl();

                    predictionListCalldk.enqueue(new Callback<PredictionList>() {
                        @Override
                        public void onResponse(Call<PredictionList> call, Response<PredictionList> response) {
                            PredictionList predictionLists=response.body();
                            maxtem.setText("Max Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getCelsius()+" \u2103");
                            mintem.setText("Min Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getLow().getCelsius()+" \u2103");
                            windsp.setText("Wind Speed : "+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getKph()+"Kph");
                        }

                        @Override
                        public void onFailure(Call<PredictionList> call, Throwable t) {

                        }
                    });

                    weatherListCall.enqueue(new Callback<WeatherList>() {
                        @Override
                        public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                            WeatherList weatherList=response.body();
                            String imageurl=weatherList.getCurrentObservation().getIconUrl();
                            Picasso.with(MainActivity.this).load(imageurl).into(image);
                            tempersture.setText(weatherList.getCurrentObservation().getTemperatureString());
                            place.setText(weatherList.getCurrentObservation().getDisplayLocation().getCity());

                        }

                        @Override
                        public void onFailure(Call<WeatherList> call, Throwable t) {

                        }
                    });

                    hourlyweahter.enqueue(new Callback<HourlyWeather>() {
                        @Override
                        public void onResponse(Call<HourlyWeather> call, Response<HourlyWeather> response) {
                            HourlyWeather weatherinstance=response.body();
                            recyclerView.setAdapter(new PostAdapter(MainActivity.this,weatherinstance.getHourlyForecast()));

                        }

                        @Override
                        public void onFailure(Call<HourlyWeather> call, Throwable t) {

                        }
                    });

                    astronomyof.enqueue(new Callback<AstronomyOfDay>() {
                        @Override
                        public void onResponse(Call<AstronomyOfDay> call, Response<AstronomyOfDay> response) {
                            AstronomyOfDay astronofday=response.body();

                            int sunrisers=Integer.parseInt(astronofday.getSunPhase().getSunrise().getHour());
                            if (sunrisers>12){
                                sunrisers=sunrisers-12;
                                sunrises.setText(sunrisers+" PM");
                            }else {
                                sunrises.setText(sunrisers+" Am");
                            }
                            int sunsine=Integer.parseInt(astronofday.getSunPhase().getSunset().getHour());
                            if (sunsine>12){
                                sunsine=sunsine-12;
                                sunsines.setText(sunsine+" PM");
                            }else{
                                sunsines.setText(sunsine+" AM");
                            }




                        }

                        @Override
                        public void onFailure(Call<AstronomyOfDay> call, Throwable t) {

                        }
                    });
                }
                if (city.toLowerCase().equals("comilla")){
                    Call<WeatherList>weatherListCall=weatherApi.getWeathercom();
                    Call<HourlyWeather>hourlyweahter=weatherApi.getHourlyWeathercom();
                    Call<AstronomyOfDay>astronomyof=weatherApi.getAstronomyofDaycom();

                    Call<PredictionList>predictionListCalldk=weatherApi.getPredictionListcom();

                    predictionListCalldk.enqueue(new Callback<PredictionList>() {
                        @Override
                        public void onResponse(Call<PredictionList> call, Response<PredictionList> response) {
                            PredictionList predictionLists=response.body();
                            maxtem.setText("Max Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getCelsius()+" \u2103");
                            mintem.setText("Min Temp :"+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getLow().getCelsius()+" \u2103");
                            windsp.setText("Wind Speed : "+predictionLists.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getKph()+"Kph");
                        }

                        @Override
                        public void onFailure(Call<PredictionList> call, Throwable t) {

                        }
                    });
                    weatherListCall.enqueue(new Callback<WeatherList>() {
                        @Override
                        public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                            WeatherList weatherList=response.body();
                            String imageurl=weatherList.getCurrentObservation().getIconUrl();
                            Picasso.with(MainActivity.this).load(imageurl).into(image);
                            tempersture.setText(weatherList.getCurrentObservation().getTemperatureString());
                            place.setText(weatherList.getCurrentObservation().getDisplayLocation().getCity());

                        }

                        @Override
                        public void onFailure(Call<WeatherList> call, Throwable t) {

                        }
                    });

                    hourlyweahter.enqueue(new Callback<HourlyWeather>() {
                        @Override
                        public void onResponse(Call<HourlyWeather> call, Response<HourlyWeather> response) {
                            HourlyWeather weatherinstance=response.body();
                            recyclerView.setAdapter(new PostAdapter(MainActivity.this,weatherinstance.getHourlyForecast()));

                        }

                        @Override
                        public void onFailure(Call<HourlyWeather> call, Throwable t) {

                        }
                    });

                    astronomyof.enqueue(new Callback<AstronomyOfDay>() {
                        @Override
                        public void onResponse(Call<AstronomyOfDay> call, Response<AstronomyOfDay> response) {
                            AstronomyOfDay astronofday=response.body();

                            int sunrisers=Integer.parseInt(astronofday.getSunPhase().getSunrise().getHour());
                            if (sunrisers>12){
                                sunrisers=sunrisers-12;
                                sunrises.setText(sunrisers+" PM");
                            }else {
                                sunrises.setText(sunrisers+" Am");
                            }
                            int sunsine=Integer.parseInt(astronofday.getSunPhase().getSunset().getHour());
                            if (sunsine>12){
                                sunsine=sunsine-12;
                                sunsines.setText(sunsine+" PM");
                            }else{
                                sunsines.setText(sunsine+" AM");
                            }




                        }

                        @Override
                        public void onFailure(Call<AstronomyOfDay> call, Throwable t) {

                        }
                    });
                }






            }
        });




    }

    public void nextActivity(View view) {

        Intent intent=new Intent(MainActivity.this,GraphViewShow.class);
        intent.putExtra("city",city);
        startActivity(intent);

    }


}
