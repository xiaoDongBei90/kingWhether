package com.fusheng.kingweather.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class DailyForecastEntity implements Serializable {

    private CondEntity cond;
    private String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;

    private TmpEntity tmp;
    private String vis;

    private WindEntity wind;


}