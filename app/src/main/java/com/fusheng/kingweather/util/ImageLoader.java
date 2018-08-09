package com.fusheng.kingweather.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.fusheng.kingweather.R;


/**
 * Created by LiXiaoWei on 2017/11/09.
 * @describe: 图片加载工具类
 */

public class ImageLoader {
    /**
     * 加载网络图片
     * @param imageUrl 网络图片路径
     * @param imageView 被设置的imageview
     */
    public static void showImage(Context context, String imageUrl, ImageView imageView) {
        if (context != null) {
            RequestOptions options = new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(context).load(imageUrl).apply(options).into(imageView);
        }
    }

    /**
     * 加载正常图片,需自定义占位图
     * @param imageUrl 网络图片路径
     * @param imageView 被设置的imageview
     * @param placeholderResId 占位图资源id
     */
    public static void showImage(Context context, String imageUrl, ImageView imageView, int placeholderResId) {
        if (context != null) {
            RequestOptions options = new RequestOptions().centerCrop().placeholder(placeholderResId).error(placeholderResId).diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(context).load(imageUrl).apply(options).into(imageView);
        }
    }

}
