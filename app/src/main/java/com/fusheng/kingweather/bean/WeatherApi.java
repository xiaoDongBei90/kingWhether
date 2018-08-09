package com.fusheng.kingweather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * @author LiXiaoWei
 * @date 2018/8/9
 * desc:
 */
@Data
public class WeatherApi {
    @SerializedName("HeWeather5")
    private List<Weather> rootList;
}
