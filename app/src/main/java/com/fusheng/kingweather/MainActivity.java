package com.fusheng.kingweather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.fusheng.kingweather.base.BaseActivity;
import com.fusheng.kingweather.home.fragment.HomeFragment;
import com.fusheng.kingweather.personal.PersonalCenter;
import com.fusheng.kingweather.picture.SecondFragment;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {
    @BindView(R.id.bnv)
    BottomNavigationView bnv;
    private HomeFragment homeFragment;
    private SecondFragment secondFragment;
    private PersonalCenter personalCenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        basicSetting();
        setListener();
    }

    private void selectFragment(int selectPosition, FragmentManager fm) {
        FragmentTransaction ft = fm.beginTransaction();
        hideAllFragment(ft);
        bnv.getMenu().getItem(selectPosition).setChecked(true);
        switch (selectPosition) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.fl_main_container, homeFragment);
                } else {
                    ft.show(homeFragment);
                }
                break;
            case 1:
                if (secondFragment == null) {
                    secondFragment = new SecondFragment();
                    ft.add(R.id.fl_main_container, secondFragment);
                } else {
                    ft.show(secondFragment);
                }
                break;
            case 2:
                if (personalCenter == null) {
                    personalCenter = new PersonalCenter();
                    ft.add(R.id.fl_main_container, personalCenter);
                } else {
                    ft.show(personalCenter);
                }
                break;
            default:
        }
        ft.commit();
    }


    private void basicSetting() {
        ButterKnife.bind(this);
        selectFragment(0, getSupportFragmentManager());
    }

    private void setListener() {
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.action_news:
                        selectFragment(0, supportFragmentManager);
                        //invokeLogin();
                       // getCategory();
                        break;
                    case R.id.action_picture:
                        selectFragment(1, supportFragmentManager);
                        break;
                    case R.id.action_personal:
                        selectFragment(2, supportFragmentManager);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

   /* private void getCategory() {
        HttpManager.getHttpService().getTaskCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<List<TaskCategory>>(this) {
                    @Override
                    public void onSuccessed(BaseResult<List<TaskCategory>> result) {

                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
                *//*.subscribe(new ApiSubscriber<List<TaskCategory>>(MainActivity.this, false, new ResultListener<List<TaskCategory>>() {
                    @Override
                    public void onNext(BaseResult<List<TaskCategory>> o) {
                        List<TaskCategory> data = o.getData();
                        String string = data.toString();
                        Log.d("task", string);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                }));*//*
    }*/

   /* private void invokeLogin() {
        String mdPassword = md5("111111");
        HashMap<String, String> map = new HashMap<>();
        map.put("acc", "13764959024");
        map.put("pwd", mdPassword);
        String jsonParams = JSON.toJSONString(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        HttpManager.getHttpService().login(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ApiSubscriber<String>(MainActivity.this, true, new ResultListener<String>() {
                    @Override
                    public void onNext(BaseResult<String> o) {
                        Log.d("login", String.valueOf("登录结果：" + o.getResult()));
                        Log.d("login", String.valueOf("token:" + o.getData()));
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }
                }));
    }*/


    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.update(str.getBytes("utf-8"));
            byte[] result = md.digest();
            StringBuffer sb = new StringBuffer(32);
            for (int i = 0; i < result.length; i++) {
                int val = result[i] & 0xff;
                if (val <= 0xf) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();//.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void hideAllFragment(FragmentTransaction ft) {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (secondFragment != null) {
            ft.hide(secondFragment);
        }
        if (personalCenter != null) {
            ft.hide(personalCenter);
        }
    }
}
