package com.fusheng.kingweather.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class HourlyForecastEntity implements Serializable {
    public String date;
    public String hum;
    public String pop;
    public String pres;
    public String tmp;
    public WindEntity wind;
}