package com.xiaopeng.myrxjavaretorofitmvpjni.util;

import android.content.Context;

/**
 * JNIUtils
 * Created by xiaopeng on 2017/11/29.
 */

public class JNIUtils {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String get3DESKey(Context context);

    public static native String get3DESVi(Context context);

}
