package com.fusheng.kingweather.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class CondEntity implements Serializable {
    @SerializedName("code_d")
    private String codeD;
    @SerializedName("code_n")
    private String codeN;
    @SerializedName("txt_d")
    private String txtD;
    @SerializedName("txt_n")
    private String txtN;
}