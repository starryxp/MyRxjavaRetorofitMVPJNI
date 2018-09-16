package com.xiaopeng.myrxjavaretorofitmvpjni.api;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * server端api
 * Created by xiaopeng on 2017/11/29.
 */

public interface MyApi {

//    @POST("user{data}")
//    Observable<QQ> getQQ(@Path("userId") String userId);

//    @GET("user/{userId}")
//    Observable<QQ> getQQ(@Path("userId") String userId);

//    //获取版本信息
//    @POST("/shop-user-web/about/getAppVersionInfo")
//    Observable<AppVersionInfoResponse> getAppVersionInfo(@Body RequestBody body);

    //获取版本信息
    @POST
    Observable<String> getAppVersionInfo(@Url String url);

    @GET
    Observable<Object> getBaidu(@Url String url);

}
