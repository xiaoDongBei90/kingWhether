package com.fusheng.kingweather.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public  class TmpEntity implements Serializable {
    private String max;
    private String min;

}