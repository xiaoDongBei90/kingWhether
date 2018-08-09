package com.fusheng.kingweather.network;

/**
 * Created by paul on 2018/5/23.
 * Description:
 */

public class RequestUrl {
    /**
     * 生产
     */
    public static final String PRODUCT_URL = "https://api.phc-dow.com/";
    public static final String TEST_URL = "http://api.testxlc.bakheet.cn/";
    public static final String WHETHER_URL = "https://free-api.heweather.com/v5/";
    public static String BASE_URL = WHETHER_URL;
    public static String BASE_IMAGE_URL = "https://xlc.phc-dow.com/";
    /**
     * 登陆
     */
    public static final String LOGIN = "user/login";
    /**
     * 获取平台车辆列表(本店)
     */
    public static final String CAR_LIST = "user/car/list";
    public static final String WORK_CLASSIFY = "item/task/category/platform/list";
}
