package com.example.destroy.jsonandroidparshingbasicrecyclerview.ApiConfigure;

import android.content.ContentValues;
import android.content.Context;
import android.provider.SyncStateContract;

import com.example.destroy.jsonandroidparshingbasicrecyclerview.AllActivity.MainActivity;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.HourlyModel.HourlyWeather;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.Prediction.Forecast;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.Prediction.PredictionList;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.SunriseSunSetModel.AstronomyOfDay;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.TenDaysForcast.SevendaysHistory;
import com.example.destroy.jsonandroidparshingbasicrecyclerview.model.WeatherList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {

    //current temperature
    @GET("api/d47b43401f960f47/conditions/q/BD/dhaka.json")
    Call<WeatherList>getWeather();

    //hourly temperature
    @GET("api/d47b43401f960f47/hourly/q/BD/Dhaka.json")
    Call<HourlyWeather>getHourlyWeather();

    //sunsine and sunrice
    @GET("api/d47b43401f960f47/astronomy/q/BD/dhaka.json")
    Call<AstronomyOfDay>getAstronomyofDay();

    //three days weather prediction
    @GET("api/d47b43401f960f47/forecast/q/BD/Dhaka.json")
    Call<PredictionList> getPredictionList();

    //7days forcast...
    @GET("api/d47b43401f960f47/forecast10day/q/BD/Dhaka.json")
    Call<SevendaysHistory>getSevendaysHistory();

    //current temperature
    @GET("api/d47b43401f960f47/conditions/q/BD/rajshahi.json")
    Call<WeatherList>getWeatherraj();

    //hourly temperature
    @GET("api/d47b43401f960f47/hourly/q/BD/rajshahi.json")
    Call<HourlyWeather>getHourlyWeatherraj();

    //sunsine and sunrice
    @GET("api/d47b43401f960f47/astronomy/q/BD/rajshahi.json")
    Call<AstronomyOfDay>getAstronomyofDayraj();

    //three days weather prediction
    @GET("api/d47b43401f960f47/forecast/q/BD/rajshahi.json")
    Call<PredictionList> getPredictionListraj();

    //7days forcast...
    @GET("api/d47b43401f960f47/forecast10day/q/BD/rajshahi.json")
    Call<SevendaysHistory>getSevendaysHistoryraj();

    //current temperature
    @GET("api/d47b43401f960f47/conditions/q/BD/chittagong.json")
    Call<WeatherList>getWeatherchit();

    //hourly temperature
    @GET("api/d47b43401f960f47/hourly/q/BD/chittagong.json")
    Call<HourlyWeather>getHourlyWeatherchit();

    //sunsine and sunrice
    @GET("api/d47b43401f960f47/astronomy/q/BD/chittagong.json")
    Call<AstronomyOfDay>getAstronomyofDaychit();

    //three days weather prediction
    @GET("api/d47b43401f960f47/forecast/q/BD/chittagong.json")
    Call<PredictionList> getPredictionListchit();

    //7days forcast...
    @GET("api/d47b43401f960f47/forecast10day/q/BD/chittagong.json")
    Call<SevendaysHistory>getSevendaysHistorychit();

    //current temperature
    @GET("api/d47b43401f960f47/conditions/q/BD/comilla.json")
    Call<WeatherList>getWeathercom();

    //hourly temperature
    @GET("api/d47b43401f960f47/hourly/q/BD/comilla.json")
    Call<HourlyWeather>getHourlyWeathercom();

    //sunsine and sunrice
    @GET("api/d47b43401f960f47/astronomy/q/BD/comilla.json")
    Call<AstronomyOfDay>getAstronomyofDaycom();

    //three days weather prediction
    @GET("api/d47b43401f960f47/forecast/q/BD/comilla.json")
    Call<PredictionList> getPredictionListcom();

    //7days forcast...
    @GET("api/d47b43401f960f47/forecast10day/q/BD/comilla.json")
    Call<SevendaysHistory>getSevendaysHistorcom();

    //current temperature
    @GET("api/d47b43401f960f47/conditions/q/BD/sylhet.json")
    Call<WeatherList>getWeathersyl();

    //hourly temperature
    @GET("api/d47b43401f960f47/hourly/q/BD/sylhet.json")
    Call<HourlyWeather>getHourlyWeathersyl();

    //sunsine and sunrice
    @GET("api/d47b43401f960f47/astronomy/q/BD/sylhet.json")
    Call<AstronomyOfDay>getAstronomyofDaysyl();

    //three days weather prediction
    @GET("api/d47b43401f960f47/forecast/q/BD/sylhet.json")
    Call<PredictionList> getPredictionListsyl();

    //7days forcast...
    @GET("api/d47b43401f960f47/forecast10day/q/BD/sylhet.json")
    Call<SevendaysHistory>getSevendaysHistorysyl();

    //current temperature
    @GET("api/d47b43401f960f47/conditions/q/BD/khulna.json")
    Call<WeatherList>getWeatherkhul();

    //hourly temperature
    @GET("api/d47b43401f960f47/hourly/q/BD/khulna.json")
    Call<HourlyWeather>getHourlyWeatherkhul();

    //sunsine and sunrice
    @GET("api/d47b43401f960f47/astronomy/q/BD/khulna.json")
    Call<AstronomyOfDay>getAstronomyofDaykhul();

    //three days weather prediction
    @GET("api/d47b43401f960f47/forecast/q/BD/khulna.json")
    Call<PredictionList> getPredictionListkhul();

    //7days forcast...
    @GET("api/d47b43401f960f47/forecast10day/q/BD/khulna.json")
    Call<SevendaysHistory>getSevendaysHistorykhul();

    //current temperature
    @GET("api/d47b43401f960f47/conditions/q/BD/barisal.json")
    Call<WeatherList>getWeatherbar();

    //hourly temperature
    @GET("api/d47b43401f960f47/hourly/q/BD/barisal.json")
    Call<HourlyWeather>getHourlyWeatherbar();

    //sunsine and sunrice
    @GET("api/d47b43401f960f47/astronomy/q/BD/barisal.json")
    Call<AstronomyOfDay>getAstronomyofDaybar();

    //three days weather prediction
    @GET("api/d47b43401f960f47/forecast/q/BD/barisal.json")
    Call<PredictionList> getPredictionListbar();

    //current temperature
    @GET("api/d47b43401f960f47/conditions/q/BD/rangpur.json")
    Call<WeatherList>getWeatherrang();

    //hourly temperature
    @GET("api/d47b43401f960f47/hourly/q/BD/rangpur.json")
    Call<HourlyWeather>getHourlyWeatherrang();

    //sunsine and sunrice
    @GET("api/d47b43401f960f47/astronomy/q/BD/rangpur.json")
    Call<AstronomyOfDay>getAstronomyofDayrang();

    //three days weather prediction
    @GET("api/d47b43401f960f47/forecast/q/BD/rangpur.json")
    Call<PredictionList> getPredictionListrang();


}
