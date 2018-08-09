package com.fusheng.kingweather.picture;

import java.io.Serializable;
import java.util.List;

/**
 * author  LiXiaoWei
 * date  2017/12/5.
 * desc:
 */

public class DiscernCarBean<T> implements Serializable{
    private int totalCount;
    private List<T> carList;

    public int getTotalcount() {
        return totalCount;
    }

    public List<T> getCarList() {
        return carList;
    }
}
