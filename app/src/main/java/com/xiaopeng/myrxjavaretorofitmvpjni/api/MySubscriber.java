package com.xiaopeng.myrxjavaretorofitmvpjni.api;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;
import com.xiaopeng.myrxjavaretorofitmvpjni.api.interneter.Result;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import rx.Subscriber;

/**
 * MySubscriber
 * Created by xiaopeng on 2017/11/30.
 */

public abstract class MySubscriber<K extends Result> extends Subscriber<String> {
    private final static String TAG = "MySubscriber";
    private Gson mGson = new Gson();

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof UnsupportedEncodingException) {//解密异常
            e.printStackTrace();
            onFailure(new Result(Result.DECRYPTION_ERROR));
        } else if (e instanceof IOException) {
            e.printStackTrace();
            onFailure(new Result(Result.NET_ERROR));
        } else {
            onFailure(new Result(Result.NET_ERROR));
            e.printStackTrace();
        }
    }

    @Override
    public void onNext(String t) {
        try {
            Logger.t(TAG).json(t);

            K result = getResult(t, mGson);
            if (result != null) {
                if (result.getRescode().equals(Result.OK)) {
                    onSucceed(result);
                } else {
                    onFailure(new Result(result.getRescode(), result.getResmsg()));
                }
            } else {
                onFailure(new Result(Result.DEFAULT_ERROR));
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
            onFailure(new Result(Result.JSON_ERROR));
        }
    }

    protected abstract K getResult(String json, Gson mGson);

    protected abstract void onSucceed(K result);

    protected abstract void onFailure(Result result);


}
