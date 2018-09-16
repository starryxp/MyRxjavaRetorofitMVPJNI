package com.xiaopeng.myrxjavaretorofitmvpjni.api.converter;

import com.xiaopeng.myrxjavaretorofitmvpjni.util.secret.SecretUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * StringConverterFactory
 * Created by xiaopeng on 2017/11/30.
 */

public class StringConverterFactory extends Converter.Factory {

    private static StringConverterFactory sInstance;

    public static StringConverterFactory create() {
        if (sInstance == null) {
            synchronized (StringConverterFactory.class) {
                if (sInstance == null) {
                    sInstance = new StringConverterFactory();
                }
            }
        }
        return sInstance;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return StringResponseConverter.INSTANCE;
        }
        //其它类型我们不处理，返回null就行
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit
            retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }
}

class StringResponseConverter implements Converter<ResponseBody, String> {
    public static final StringResponseConverter INSTANCE = new StringResponseConverter();

    @Override
    public String convert(ResponseBody value) throws IOException {
        return SecretUtil.decryption(value.string());
    }
}

class StringRequestConverter<T> implements Converter<T, RequestBody> {
    @Override
    public RequestBody convert(T value) throws IOException {
        return null;
    }
}
