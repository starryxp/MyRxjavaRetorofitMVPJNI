package com.xiaopeng.myrxjavaretorofitmvpjni.api.converter;

import com.orhanobut.logger.Logger;
import com.xiaopeng.myrxjavaretorofitmvpjni.util.secret.SecretUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 解密响应
 * Created by xiaopeng on 2017/11/29.
 */

public class DecodeJsonResponseBodyConverter implements Converter<ResponseBody, String> {
    private final static String TAG = "DecodeJsonResponseBodyConverter";
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    //    private final Gson mGson;//gson对象
//    private final TypeAdapter<T> adapter;


    /**
     * 构造器
     */
    public DecodeJsonResponseBodyConverter(/*Gson gson,*/ /*TypeAdapter<T> adapter*/) {
//        this.mGson = gson;
//        this.adapter = adapter;
    }

    /**
     * 转换
     *
     * @param value
     * @return
     * @throws IOException
     */
    @Override
    public String convert(ResponseBody value) throws IOException {
//        String response = value.string();
////        String strResult = response.substring(1, response.length() - 1);
//        String result = SecretUtil.decryption(response);//解密
//        Logger.t(TAG).d("解密的服务器数据：" + result);
//        JsonReader jsonReader = mGson.newJsonReader(ResponseBody.create(MEDIA_TYPE, result).charStream());
//        try {
//            return adapter.read(jsonReader);
//        } finally {
//            value.close();
//        }
        //解密字符串
        Logger.t(TAG).json(/*"解密的服务器数据：" +*/ SecretUtil.decryption(value.string()));
//        return adapter.fromJson(SecretUtil.decryption(value.string()));
        try {
            return SecretUtil.decryption(value.string());
        } finally {
            value.close();
        }
    }
}
