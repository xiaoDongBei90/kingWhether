package com.fusheng.kingweather.picture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.fusheng.kingweather.R;
import com.fusheng.kingweather.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;


public class SecondFragment extends BaseFragment {
    @BindView(R.id.rv_test)
    RecyclerView rvTest;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int pageNum = 1;
    private int pageSize = 20;
    private List<CarInfo> carLists;
    private PictureAdapter pictureAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_picture;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
       /* initRv();
        getCarList(pageNum, true);
        setListener();*/
    }

/*    private void initRv() {
        carLists = new ArrayList<>();
        pictureAdapter = new PictureAdapter(R.layout.item_car_manager, carLists);
        rvTest.setLayoutManager(new LinearLayoutManager(SecondFragment.this.getActivity()));
        rvTest.setAdapter(pictureAdapter);
    }

    private void getCarList(final int pageNum, boolean isShowProgressDialog) {
        HttpManager.getHttpService().getCarList(pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<DiscernCarBean<CarInfo>>(this.getActivity()) {
                    @Override
                    public void onSuccessed(BaseResult<DiscernCarBean<CarInfo>> result) {
                        srl.finishRefresh();
                        srl.finishLoadmore();
                        DiscernCarBean<CarInfo> data = result.getData();
                        List<CarInfo> list = data.getCarList();
                        if (pageNum == 1) {
                            carLists.clear();
                        }
                        carLists.addAll(list);
                        pictureAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });

    }

    private void setListener() {
        srl.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //1
                pageNum++;
                getCarList(pageNum, false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                getCarList(pageNum, false);
            }
        });
    }*/
}
