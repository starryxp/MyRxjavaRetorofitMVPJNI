package com.xiaopeng.myrxjavaretorofitmvpjni.app;

import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.xiaopeng.myrxjavaretorofitmvpjni.BuildConfig;
import com.xiaopeng.myrxjavaretorofitmvpjni.R;
import com.xiaopeng.myrxjavaretorofitmvpjni.app.base.BaseApplication;
import com.xiaopeng.myrxjavaretorofitmvpjni.util.JNIUtils;
import com.xiaopeng.myrxjavaretorofitmvpjni.util.secret.SecretUtil;

/**
 * Application
 * Created by xiaopeng on 2017/11/29.
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            if (TextUtils.isEmpty(SecretUtil.SECRET_KEY)) {
                SecretUtil.SECRET_KEY = JNIUtils.get3DESKey(this);
            }
            if (TextUtils.isEmpty(SecretUtil.IV)) {
                SecretUtil.IV = JNIUtils.get3DESVi(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(getString(R.string.app_name))   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        //设置该CrashHandler为程序的默认处理器
//        if (BuildConfig.DEBUG) {
//            CustomExceptionHandler crashHandler = CustomExceptionHandler.getInstance();
//            crashHandler.init(getApplicationContext());
//        }

    }

}
