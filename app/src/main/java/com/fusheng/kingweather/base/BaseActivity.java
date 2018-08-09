package com.fusheng.kingweather.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * author  LiXiaoWei
 * date  2018/5/24.
 * desc:
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init(savedInstanceState);
    }

    public abstract int getLayoutId();

    public abstract void init(Bundle savedInstanceState);
}
