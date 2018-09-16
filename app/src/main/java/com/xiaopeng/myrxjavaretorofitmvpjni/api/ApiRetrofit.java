package com.xiaopeng.myrxjavaretorofitmvpjni.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.xiaopeng.myrxjavaretorofitmvpjni.api.base.BaseApiRetrofit;
import com.xiaopeng.myrxjavaretorofitmvpjni.api.converter.StringConverterFactory;
import com.xiaopeng.myrxjavaretorofitmvpjni.model.request.AppVersionInfoRequest;
import com.xiaopeng.myrxjavaretorofitmvpjni.util.Constants;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * 使用Retrofit对网络请求进行配置
 * Created by xiaopeng on 2017/11/29.
 */

public class ApiRetrofit extends BaseApiRetrofit {

    private static final String TAG = "ApiRetrofit";
    private MyApi myApi;
    private static volatile ApiRetrofit mInstance;

    public static ApiRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (ApiRetrofit.class) {
                if (mInstance == null) {
                    mInstance = new ApiRetrofit();
                }
            }
        }
        return mInstance;
    }

    private ApiRetrofit() {
        super();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //在构造方法中完成对Retrofit接口的初始化
        myApi = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getClient())
//                .addConverterFactory(JsonConverterFactory.create(gson))
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MyApi.class);
    }

    private RequestBody getRequestBody(Object obj) {
        String route = new Gson().toJson(obj);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
        return body;
    }

    //获取版本信息
    public Observable<String> getAppVersionInfo(String appVersion, String channel) {
        String url = Constants.BASE_URL + "/shop-user-web/about/getAppVersionInfo?" + new AppVersionInfoRequest(appVersion, channel).toEncryptString();
        Logger.t(TAG).d(url);
        return myApi.getAppVersionInfo(url);
    }

    public Observable<Object> getBaidu() {
        return myApi.getBaidu("https://www.baidu.com");
    }

}
