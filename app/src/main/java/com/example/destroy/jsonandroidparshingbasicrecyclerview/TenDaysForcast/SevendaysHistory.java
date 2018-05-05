
package com.example.destroy.jsonandroidparshingbasicrecyclerview.TenDaysForcast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SevendaysHistory {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("forecast")
    @Expose
    private Forecast forecast;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

}
