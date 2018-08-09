package com.fusheng.kingweather.base;

/**
 * Created by paul on 2018/5/23.
 * Description:
 */

public class BaseResult<T> {
    private String desc;
    private int result;
    private T data;

    public boolean isSuccess() {
        return 0 == result;
    }

    public String getDesc() {
        return desc;
    }

    public int getResult() {
        return result;
    }

    public T getData() {
        return data;
    }
}
