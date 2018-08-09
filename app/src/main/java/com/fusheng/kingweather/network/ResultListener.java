package com.fusheng.kingweather.network;

import com.fusheng.kingweather.base.BaseResult;

/**
 * author  LiXiaoWei
 * date  2018/6/26.
 * desc:
 */

public interface ResultListener<T> {
    void onNext(BaseResult<T> o);
    void onCompleted();
    void onError(Throwable t);
}
