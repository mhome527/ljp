package vn.jp.language.ljp.utils;


import vn.jp.language.ljp.BuildConfig;

/**
 * Created by huynhtd on 9/29/2016.
 */

public class Log {
    private static String myTag = "htd-";

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            android.util.Log.i(myTag + tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            android.util.Log.e(myTag + tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            android.util.Log.d(myTag + tag, msg);
        }
    }

    public static void trace(Exception e){
        if(BuildConfig.DEBUG){
            e.printStackTrace();
        }
    }

}
