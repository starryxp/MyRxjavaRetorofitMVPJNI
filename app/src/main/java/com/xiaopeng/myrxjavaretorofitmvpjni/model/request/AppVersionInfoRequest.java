package com.xiaopeng.myrxjavaretorofitmvpjni.model.request;

import com.xiaopeng.myrxjavaretorofitmvpjni.api.interneter.ParamsSet;

/**
 * Created by xiaopeng on 2017/11/29.
 */

public class AppVersionInfoRequest extends ParamsSet.Param {
    private static final long serialVersionUID = 4438735366324281651L;
    private String appVersion;
    private String channel;

    public AppVersionInfoRequest(String appVersion, String channel) {
        this.appVersion = appVersion;
        this.channel = channel;
    }
}
