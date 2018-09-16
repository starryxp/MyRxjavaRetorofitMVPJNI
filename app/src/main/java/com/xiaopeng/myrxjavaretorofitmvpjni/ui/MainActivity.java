package com.xiaopeng.myrxjavaretorofitmvpjni.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xiaopeng.myrxjavaretorofitmvpjni.R;
import com.xiaopeng.myrxjavaretorofitmvpjni.api.ApiRetrofit;
import com.xiaopeng.myrxjavaretorofitmvpjni.api.MySubscriber;
import com.xiaopeng.myrxjavaretorofitmvpjni.api.interneter.Result;
import com.xiaopeng.myrxjavaretorofitmvpjni.model.response.AppVersionInfoResponse;
import com.xiaopeng.myrxjavaretorofitmvpjni.util.ConfigUntil;
import com.xiaopeng.myrxjavaretorofitmvpjni.util.JNIUtils;
import com.xiaopeng.myrxjavaretorofitmvpjni.util.UIUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConfigUntil.getSignature(this);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText("key = " + JNIUtils.get3DESKey(this) + "vi = " + JNIUtils.get3DESVi(this));

//        ApiRetrofit.getInstance().getBaidu()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Object>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Object o) {
//
//                    }
//                });

//        mSubscription = ApiRetrofit.getInstance().getAppVersionInfo("1.0.0", "yingyongbao")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Logger.t(TAG).d(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(String appVersionInfoResponse) {
//                        Logger.t(TAG).json(appVersionInfoResponse);
//                    }
//                });

        mSubscription = ApiRetrofit.getInstance().getAppVersionInfo("1.0.0", "yingyongbao")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<AppVersionInfoResponse>() {
                    @Override
                    protected AppVersionInfoResponse getResult(String json, Gson mGson) {
//                        AppVersionInfoResponse result = mGson.fromJson(json, new TypeToken<AppVersionInfoResponse>() {
//                        }.getType());
                        return mGson.fromJson(json, AppVersionInfoResponse.class);
                    }

                    @Override
                    protected void onSucceed(AppVersionInfoResponse result) {
                        UIUtils.showToast(result.getMsg(MainActivity.this));
                    }

                    @Override
                    protected void onFailure(Result result) {
                        UIUtils.showToast(result.getMsg(MainActivity.this));
                    }
                });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

    }
}
