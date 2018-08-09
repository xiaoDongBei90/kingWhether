package com.fusheng.kingweather.network;

import android.app.ProgressDialog;
import android.content.Context;

import com.fusheng.kingweather.base.BaseResult;

import rx.Subscriber;

/**
 * author  LiXiaoWei
 * date  2018/6/26.
 * desc:
 */

public abstract class ApiSubscriber<T> extends Subscriber<BaseResult<T>> {
    private ResultListener<T> mListener;
    private ProgressDialog dialog;
    private boolean isShowProgressDialog;

    public ApiSubscriber(Context context) {
        super();
        this.isShowProgressDialog = true;
        dialog = new ProgressDialog(context);
        dialog.setTitle("我是dialog");
        dialog.setMessage("我是内容");
    }

    public ApiSubscriber(Context context, ResultListener<T> mListener) {
        super();
        this.mListener = mListener;
        this.isShowProgressDialog = true;
        dialog = new ProgressDialog(context);
        dialog.setTitle("我是dialog");
        dialog.setMessage("我是内容");
    }

    public ApiSubscriber(Context context, boolean isShowProgressDialog, ResultListener<T> mListener) {
        super();
        this.mListener = mListener;
        this.isShowProgressDialog = isShowProgressDialog;
        if (isShowProgressDialog) {
            dialog = new ProgressDialog(context);
            dialog.setTitle("我是dialog");
            dialog.setMessage("我是内容");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isShowProgressDialog) {
            dialog.show();
        }
    }

    @Override
    public void onNext(BaseResult<T> result) {
        onSuccessed(result);
        /*if (result.isSuccess()) {
            mListener.onNext(result);
        }*/
    }

    @Override
    public void onCompleted() {
        if (isShowProgressDialog) {
            dialog.dismiss();
        }
        //mListener.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e);
        /*if (isShowProgressDialog) {
            dialog.dismiss();
        }
        mListener.onError(e);*/
    }

    public abstract void onSuccessed(BaseResult<T> result);

    public abstract void onFailed(Throwable e);
}
