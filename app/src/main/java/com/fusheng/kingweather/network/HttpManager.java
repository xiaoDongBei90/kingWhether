package com.fusheng.kingweather.network;

import android.util.Log;

import com.fusheng.kingweather.base.WhetherApp;
import com.fusheng.kingweather.util.NetworkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paul on 2018/5/23.
 * Description:
 */

public class HttpManager {
    private static HttpService httpService;
    private static volatile Retrofit retrofit;

    public static HttpService getHttpService() {
        if (httpService == null) {
            httpService = getRetrofit().create(HttpService.class);
        }
        return httpService;
    }

    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("weatherdata", "-------" + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //设置 请求的缓存的大小跟位置
        //File cacheFile = new File(ProApplication.getmContext().getCacheDir(), "cache");
        //50Mb 缓存的大小
        //Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(addCommonHeaderInterceptor())
                /*.cache(cache)
                .addInterceptor(addCommonParameterInterceptor())*/
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();
        retrofit = builder
                .baseUrl(RequestUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 设置公共请求头
     */
    public static Interceptor addCommonHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();
                //13764959024   111111
                //a3eebb9d7d8b0a3b55a43717780ad025f58d8577
                //f3896f3b566d7bb66e42b6355e1488b002e9886c
                builder.addHeader("token", "f3896f3b566d7bb66e42b6355e1488b002e9886c");
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        };
    }

    /**
     * 添加公共参数
     */
    public static Interceptor addCommonParameterInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl.Builder builder = originalRequest.url().newBuilder();
                builder.addQueryParameter("dljs", "sjdlfkj");
                HttpUrl modifiedUrl = builder.build();
                Request build = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(build);
            }
        };
    }

    /**
     * 设置缓存
     */
    private static Interceptor addCacheInterceptor() {
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtil.isNetworkAvailable(WhetherApp.getWhetherApp())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtil.isNetworkAvailable(WhetherApp.getWhetherApp())) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" +
                                    maxStale)
                            .removeHeader("nyn")
                            .build();
                }
                return response;
            }
        };
        return cacheInterceptor;
    }
}
