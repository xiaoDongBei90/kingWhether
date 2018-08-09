package com.fusheng.kingweather.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class SuggestionEntity implements Serializable {
    private Comf comf;

    private Comf cw;

    private Comf drsg;

    private Comf flu;

    private Comf sport;

    private Comf trav;

    private Comf uv;

}