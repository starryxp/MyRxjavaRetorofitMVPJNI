package com.xiaopeng.myrxjavaretorofitmvpjni.model.response;


import com.xiaopeng.myrxjavaretorofitmvpjni.api.interneter.Result;

import java.io.Serializable;

/**
 * 获取本版信息Response
 * Created by xiaopeng on 2016/9/7.
 */
public class AppVersionInfoResponse extends Result implements Serializable {
    private static final long serialVersionUID = -7571993757515155284L;
    /**
     * appVersion : 1.7.7
     * updatePer : admin
     * downloadUrl : http://app-global.pgyer.com/103623bba9b58ef493a5afcd5cea4a44.apk?e=1505472516&attname=Test1.7.7-2017091501-BAIDU-release.apk&token=6fYeQ7_TVB5L0QSzosNFfw2HU8eJhAirMF5VxV9G:QkEt27Vpjk6REaL_FE6vDkj_pHA=&sign=7dabcea1b764c3fff945bcc120de4473&t=59bbb004
     * updateTime : 1498665600000
     * createPer : admin
     * osName : ANDROID
     * updateDesc : 1.	新增商品预售模块，更多热门商品让您提前入手；
     * 2.	商品详情页全面升级，给您带来全新体验；
     * 3.	个人中心新增我的活动，更多惊喜敬请期待；
     * 4.	首页UI优化，操作更便捷，浏览更顺畅。
     * createTime : 1498665600000
     * id : 29
     * status : 1
     * updateType : 2
     */

    private String appVersion;
    private String updatePer;
    private String downloadUrl;
    private long updateTime;
    private String createPer;
    private String osName;
    private String updateDesc;
    private long createTime;
    private int id;
    private String status;
    private String updateType;

    public AppVersionInfoResponse(String rescode, String resmsg_cn) {
        super(rescode, resmsg_cn);
    }


    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getUpdatePer() {
        return updatePer;
    }

    public void setUpdatePer(String updatePer) {
        this.updatePer = updatePer;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatePer() {
        return createPer;
    }

    public void setCreatePer(String createPer) {
        this.createPer = createPer;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getUpdateDesc() {
        return updateDesc;
    }

    public void setUpdateDesc(String updateDesc) {
        this.updateDesc = updateDesc;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }
}
