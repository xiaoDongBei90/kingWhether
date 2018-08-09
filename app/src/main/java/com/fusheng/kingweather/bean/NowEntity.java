package com.fusheng.kingweather.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class NowEntity implements Serializable {

    private CondEntity cond;
    private String fl;
    private String hum;
    private String pcpn;
    private String pres;
    private String tmp;
    private String vis;
    private WindEntity wind;
}