package com.fusheng.kingweather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Weather {

    private AqiEntity aqi;

    private BasicEntity basic;

    @SerializedName("daily_forecast")
    private List<DailyForecastEntity> dailyForecast;

    @SerializedName("hourly_forecast")
    private List<HourlyForecastEntity> hourlyForecast;

    private NowEntity now;

    private String status;

    private SuggestionEntity suggestion;

}
