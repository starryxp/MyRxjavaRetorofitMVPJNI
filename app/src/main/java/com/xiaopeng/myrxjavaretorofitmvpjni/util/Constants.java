package com.xiaopeng.myrxjavaretorofitmvpjni.util;


public class Constants {

    //是否https访问
    public static final boolean isHttps = true;
    //服务器是否正在维护
    public static final String HOST = isHttps ? "https://" : "http://";

    /**
     * 正式环境ip
     */
    public static final String URL_PRODUCT = HOST + "www.zhihuiup.com";

    /**
     * 测试环境
     */
    public static final String BASE_SIT = HOST + "test.zhihuiup.com";

    /**
     * UAT环境
     */
    public static final String BASE_UAT = HOST + "shop.zhihuiup.com";

    /**
     * IP
     */
    public static final String BASE_URL = BASE_SIT;

}

