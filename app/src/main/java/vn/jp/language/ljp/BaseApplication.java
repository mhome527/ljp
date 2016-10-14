package vn.jp.language.ljp;

import android.app.Application;
import android.util.Log;

/**
 * Created by huynhtran on 11/10/16.
 */
public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        String temp;
        String url;
        String domain;


        androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }


    private Thread.UncaughtExceptionHandler androidDefaultUEH;
    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            Log.e("TestApplication", "Uncaught exception is: ", ex);
            androidDefaultUEH.uncaughtException(thread, ex);
            Log.e("BaseApplication", "crack!!! " + ex.getMessage());
            if (BuildConfig.DEBUG)
                ex.printStackTrace();
            //FirebaseCrash.report(ex);

        }
    };

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }

   /* public DBComponent getDbComponent(){
        return mDbComponent;
    }*/
}
