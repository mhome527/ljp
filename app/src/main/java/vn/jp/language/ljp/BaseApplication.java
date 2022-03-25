package vn.jp.language.ljp;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;

import vn.jp.language.ljp.db.SqlLiteCopyDbHelper;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Prefs;

/**
 * Created by huynhtran on 11/10/16.
 */
public class BaseApplication extends Application {

    private static String TAG = "BaseApplication";
    private static BaseApplication mInstance;
    public static FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        /////////////// Import DB
        Prefs pref = new Prefs(this.getApplicationContext());
        String strDB = pref.getStringValue("", Constant.KEY_UPDATE);
        if(strDB.equals("") || !strDB.equals(Constant.KEY_UPDATE) ) {
            Log.i(TAG, "Delete database....");
            this.deleteDatabase(Constant.DB_NAME);
        }

        SqlLiteCopyDbHelper dbHelper = new SqlLiteCopyDbHelper(this);
//        SQLiteDatabase.loadLibs(this);
        if(dbHelper.openDataBase()) {
            pref.putStringValue(Constant.KEY_UPDATE, Constant.KEY_UPDATE);
        }
        else
            Log.e(TAG, "Import Error!!!!!");

        ////
        pref.putIntValue(0, Constant.DB_DOWNLOADING);

        androidDefaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }


    private Thread.UncaughtExceptionHandler androidDefaultUEH;
    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            Log.e(TAG, "Uncaught exception is: " + ex.getMessage());
            androidDefaultUEH.uncaughtException(thread, ex);
            Log.e(TAG, "crack!!! " + ex.getMessage());
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
