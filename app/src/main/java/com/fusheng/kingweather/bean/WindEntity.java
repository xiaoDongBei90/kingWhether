package com.fusheng.kingweather.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class WindEntity implements Serializable {
    private String deg;
    private String dir;
    private String sc;
    private String spd;

}