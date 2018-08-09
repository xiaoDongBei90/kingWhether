package com.fusheng.kingweather.bean;

import java.io.Serializable;

import lombok.Data;

@Data
class AqiEntity implements Serializable {

    private CityEntity city;
}