package com.fusheng.kingweather.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fusheng.kingweather.R;
import com.fusheng.kingweather.base.BaseFragment;
import com.fusheng.kingweather.base.BaseResult;
import com.fusheng.kingweather.bean.Weather;
import com.fusheng.kingweather.network.ApiSubscriber;
import com.fusheng.kingweather.network.HttpManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author  LiXiaoWei
 * date  2018/5/24.
 * desc:
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.srl)
    SmartRefreshLayout srl;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        setListener();
    }

    private void setListener() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getWhetherData();
            }
        });
    }

    private void getWhetherData() {
        HttpManager.getHttpService().getWeather("上海", "282f3846df6b41178e4a2218ae083ea7")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<List<Weather>>(HomeFragment.this.getActivity()) {
                    @Override
                    public void onSuccessed(BaseResult<List<Weather>> result) {
                        srl.finishRefresh();
                        List<Weather> data = result.getData();
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }
}
