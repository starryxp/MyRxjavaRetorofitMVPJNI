package com.xiaopeng.myrxjavaretorofitmvpjni.api.interneter;

import android.app.Activity;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.xiaopeng.myrxjavaretorofitmvpjni.R;
import com.xiaopeng.myrxjavaretorofitmvpjni.util.ConfigUntil;

import java.io.Serializable;

/**
 * 响应基类
 * Created by xiaopeng on 2017/11/29.
 */
public class Result implements Serializable {
    private static final long serialVersionUID = -593782402622707451L;
    private static final String TAG = "Result";
    public static final String NET_ERROR = "-1";
    public static final String JSON_ERROR = "-2";
    public static final String FORM_CHECK_ERROR = "-3";
    public static final String LOGIN_ERROR = "-4";
    public static final String CONFIG_ERROR = "-5";
    public static final String DECRYPTION_ERROR = "-6";
    public static final String ENCRYPTION_ERROR = "-7";
    public static final String PARAM_ERROR = "-8";
    public static final String NET_ERROR_404 = "404";
    public static final String OK = "00000";
    public static final String ORTHER_ERROR = "00002";
    public static final String CUSTOM_ERROR = "00001";
    public static final String DEFAULT_ERROR = "-1000";
    public static final String LOGINED_ERROR = "00004";

    /**
     * response message
     */
    private String resmsg;

    /**
     * response code
     */
    private String rescode;

    public Result(String rescode) {
        this.rescode = rescode;
    }

    public Result(String rescode, String resmsg) {
        this.rescode = rescode;
        this.resmsg = resmsg;
    }

    public String getRescode() {
        return rescode;
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }

    public String getResmsg() {
        return resmsg;
    }

    public void setResmsg(String resmsg) {
        this.resmsg = resmsg;
    }

    public String getMsg(Activity context) {
        Logger.t(TAG).d("resmsg：" + resmsg);
        Logger.t(TAG).d("rescode：" + rescode);
        if (TextUtils.isEmpty(resmsg)) {
            if (rescode.equals(OK)) {
                resmsg = context.getString(R.string.resmsg_success);
            } else if (rescode.equals(DEFAULT_ERROR)) {
                resmsg = context.getString(R.string.resmsg_default_error);
            } else if (rescode.equals(NET_ERROR)) {
                resmsg = context.getString(R.string.resmsg_default_error);
            } else if (rescode.equals(JSON_ERROR)) {
                resmsg = context.getString(R.string.resmsg_json_error);
            } else if (rescode.equals(DECRYPTION_ERROR)) {
                resmsg = context.getString(R.string.resmsg_decryption_error);
            } else if (rescode.equals(ENCRYPTION_ERROR)) {
                resmsg = context.getString(R.string.resmsg_encryption_error);
            } else if (rescode.equals(PARAM_ERROR)) {
                resmsg = context.getString(R.string.resmsg_param_error);
            } else {
                resmsg = context.getString(R.string.resmsg_default_error);
            }
        } else {
            if (!ConfigUntil.containChineseCharacter(resmsg)) {
                resmsg = context.getString(R.string.resmsg_default_error);
            }
        }
        return resmsg;
    }

}
