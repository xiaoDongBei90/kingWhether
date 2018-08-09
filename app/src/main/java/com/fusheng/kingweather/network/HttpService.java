package com.fusheng.kingweather.network;

import com.fusheng.kingweather.base.BaseResult;
import com.fusheng.kingweather.bean.MultiNewsArticleBean;
import com.fusheng.kingweather.bean.NewsCommentBean;
import com.fusheng.kingweather.bean.Weather;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by paul on 2018/5/23.
 * Description:
 */

public interface HttpService {
    String host = "http://is.snssdk.com/";

    /**
     * 获取个性化新闻
     * 深圳 http://is.snssdk.com/api/news/feed/v58/?iid=5034850950&device_id=6096495334&category=news_society
     * 深圳 http://lf.snssdk.com/api/news/feed/v58/?iid=12507202490&device_id=37487219424&category=news_society
     * 天津 http://ib.snssdk.com/api/news/feed/v58/?
     * 北京 http://iu.snssdk.com/api/news/feed/v58/?
     * @param iid 用户ID
     * @param deviceId 设备ID
     * @param category 新闻/图片/视频栏目
     */
    @GET("api/news/feed/v58/")
    Observable<ResponseBody> getNewArticle(@Query("iid") String iid,
                                           @Query("device_id") String deviceId,
                                           @Query("category") String category);

    @GET("http://is.snssdk.com/api/news/feed/v62/?iid=5034850950&device_id=6096495334&refer=1&count=20&aid=13")
    Observable<MultiNewsArticleBean> getNewsArticle(
            @Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);

    @GET("http://lf.snssdk.com/api/news/feed/v62/?iid=12507202490&device_id=37487219424&refer=1&count=20&aid=13")
    Observable<MultiNewsArticleBean> getNewsArticle2(
            @Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);

    /**
     * 获取新闻评论
     * 按热度排序
     * http://is.snssdk.com/article/v53/tab_comments/?group_id=6314103921648926977&offset=0&tab_index=0
     * 按时间排序
     * http://is.snssdk.com/article/v53/tab_comments/?group_id=6314103921648926977&offset=0&tab_index=1
     * @param groupId 新闻ID
     * @param offset 偏移量
     */
    @GET("http://is.snssdk.com/article/v53/tab_comments/")
    Observable<NewsCommentBean> getNewsComment(
            @Query("group_id") String groupId,
            @Query("offset") int offset);

   /* @GET(RequestUrl.CAR_LIST)
    Observable<BaseResult<DiscernCarBean<CarInfo>>> getCarList(@Query("pageNum") int pageNum, @Query("pageSize") int pageSize);*/

    /**
     * 获取登陆信息
     */
   /* @POST(RequestUrl.LOGIN)
    Observable<BaseResult<String>> login(@Body RequestBody body);*/
/*
    @GET(RequestUrl.WORK_CLASSIFY)
    Observable<BaseResult<List<TaskCategory>>> getTaskCategory();*/
    @GET("weather")
    Observable<BaseResult<List<Weather>>> getWeather(@Query("city") String city, @Query("key") String key);
}
