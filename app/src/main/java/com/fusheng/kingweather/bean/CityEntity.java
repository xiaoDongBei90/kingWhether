package com.fusheng.kingweather.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class CityEntity implements Serializable {
    private String aqi;
    private String co;
    private String no2;
    private String o3;
    private String pm10;
    private String pm25;
    private String qlty;
    private String so2;

}