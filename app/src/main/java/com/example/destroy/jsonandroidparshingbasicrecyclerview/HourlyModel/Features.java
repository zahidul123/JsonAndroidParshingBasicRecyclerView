
package com.example.destroy.jsonandroidparshingbasicrecyclerview.HourlyModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features {

    @SerializedName("hourly")
    @Expose
    private Integer hourly;

    public Integer getHourly() {
        return hourly;
    }

    public void setHourly(Integer hourly) {
        this.hourly = hourly;
    }

}
