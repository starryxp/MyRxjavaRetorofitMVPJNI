package com.xiaopeng.myrxjavaretorofitmvpjni.api.interneter;

import android.app.Activity;

/**
 * 自定义异常
 * Created by xiaopeng on 2017/11/29.
 */

public class MyException extends Exception {

    private static final long serialVersionUID = -4411370626099507426L;
    private Result mResult;

    public MyException(Result result) {
        mResult = result;
    }

    public MyException(String detailMessage, Result result) {
        super(detailMessage);
        mResult = result;
    }

    public MyException(Throwable throwable, Result result) {
        super(throwable);
        mResult = result;
    }

    public MyException(String detailMessage) {
        super(detailMessage);
        mResult = new Result(Result.DEFAULT_ERROR, detailMessage);
    }

    public String getCode() {
        return mResult.getRescode();
    }

    public String getMsg(Activity context) {
        if (mResult != null) {
            return mResult.getMsg(context);
        }
        return getMessage();
    }

    public Result getResult() {
        return mResult;
    }

}
