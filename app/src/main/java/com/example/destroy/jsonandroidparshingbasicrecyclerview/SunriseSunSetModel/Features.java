
package com.example.destroy.jsonandroidparshingbasicrecyclerview.SunriseSunSetModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features {

    @SerializedName("astronomy")
    @Expose
    private Integer astronomy;

    public Integer getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Integer astronomy) {
        this.astronomy = astronomy;
    }

}
