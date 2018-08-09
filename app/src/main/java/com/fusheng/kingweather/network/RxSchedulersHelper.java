package com.fusheng.kingweather.network;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author  LiXiaoWei
 * date  2018/6/29.
 * desc:处理Rx线程
 */

public class RxSchedulersHelper {
    public static <T> Observable.Transformer<T, T> ioMain() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
