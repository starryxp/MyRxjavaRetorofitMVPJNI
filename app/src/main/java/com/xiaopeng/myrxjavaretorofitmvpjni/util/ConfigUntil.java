package com.xiaopeng.myrxjavaretorofitmvpjni.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.orhanobut.logger.Logger;


/**
 * @author Mzc.wang
 * @version 2014年7月18日下午5:48:41
 */
public class ConfigUntil {

    private static final String TAG = "ConfigUntil";
    // 网站 或 自动更新：EDAIWEB
    private static final String META_DEFAULT = "WEBSITE";
    //    private static final String META__KEY_DEFAULT = "CHANNEL";
    private static final String META__KEY_DEFAULT = "UMENG_CHANNEL";

    /**
     * 获取签名
     *
     * @return
     */
    public static String getSignature(Activity context) {

        String signature = "";
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            android.content.pm.Signature[] sisasd = info.signatures;
            signature = sisasd[0].toCharsString();

        } catch (PackageManager.NameNotFoundException e) {
            Logger.t(TAG).d(e.getMessage(), e);
            signature = "";
        }
        Logger.t(TAG).d("signature: " + signature);
        return signature;
    }

    // 判断sim卡是否存在
    public static boolean isSimExist(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getSimState() == TelephonyManager.SIM_STATE_READY;
    }

    /**
     * // 判断是否是包含中文字符
     *
     * @param chineseStr
     * @return
     */
    public static boolean containChineseCharacter(String chineseStr) {
        char[] charArray = chineseStr.toCharArray();
        for (char aCharArray : charArray) {
            if ((aCharArray >= 0x4e00) && (aCharArray <= 0x9fff)) {
                // Java判断一个字符串是否有中文是利用Unicode编码来判断，
                // 因为中文的编码区间为：0x4e00--0x9fbb
                return true;
            }
        }
        return false;
    }

    /**
     * 获取渠道号
     *
     * @param context 上下文环境
     * @return 返回渠道value
     */
    public static String getMetaData(Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            Object value = ai.metaData.get(META__KEY_DEFAULT);
            if (value != null) {
                Logger.t(TAG).d("meta:" + value.toString());
                return value.toString();
            }
        } catch (Exception e) {
            Logger.t(TAG).e(e.getMessage(), e);
        }
        return META_DEFAULT;
    }

    //判断是否是含有出中文以外其他字符
    public static boolean isChinese(String chineseStr) {
        char[] charArray = chineseStr.toCharArray();
        for (char aCharArray : charArray) {
            if ((aCharArray < 0x4e00) || (aCharArray > 0x9fff)) {
                //Java判断一个字符串是否有中文是利用Unicode编码来判断，
                //  因为中文的编码区间为：0x4e00--0x9fff
                return false;
            }
        }
        return true;
    }

    /**
     * 网络已经连接，然后去判断是wifi连接还是GPRS连接
     * 设置一些自己的逻辑调用
     *
     * @param context
     * @return 0、无网络连接 1、wifi 2、移动网络
     */
    public static int checkNetwork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null) {
            NetworkInfo.State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            if (gprs == NetworkInfo.State.CONNECTED || gprs == NetworkInfo.State.CONNECTING) {
//            Toast.makeText(this, "wifi is open! gprs", Toast.LENGTH_SHORT).show();
                return 2;
            }
            //判断为wifi状态下才加载广告，如果是GPRS手机网络则不加载！
            if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
//            Toast.makeText(this, "wifi is open! wifi", Toast.LENGTH_SHORT).show();
                return 1;
            }
        }
        return 0;
    }

    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            return "";
        }
    }

}
