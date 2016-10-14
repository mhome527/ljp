package vn.jp.language.ljp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import vn.jp.language.ljp.utils.Log;


/**
 * Created by huynhtran on 10/11/16
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    public static final String DATABASE_NAME = "ljp.db";
    public static final int DATABASE_VERSION = 1;
    private static DatabaseHelper dbHelper = null;
    private static SQLiteDatabase db = null;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // storing the object of this class to dbHelper
        dbHelper = this;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.i(TAG, "onCreate CREATE TABLE....");


//        NotificationTable.onCreate(database);
//        WardTable.onCreate(database);
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        Log.i(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);

//        NotificationTable.onUpgrade(database, oldVersion, newVersion);
//        WardTable.onUpgrade(database, oldVersion, newVersion);
    }

    public Cursor executeQuery(String sql) {
        Log.i(TAG, "sql:" + sql);
        Cursor mCursor = db.rawQuery(sql, null);
        return mCursor;
    }

    public boolean executeDMLQuery(String sql) {
        try {
            Log.i(TAG, "sql:" + sql);
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public static DatabaseHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context);
            openConnecion();
        }
        return dbHelper;
    }

    // will be called only once when singleton is created
    private static void openConnecion() {
        if (db == null) {
            db = dbHelper.getWritableDatabase();
        }
    }

    public static void deleteTable(Context context, String tableName) {
        DatabaseHelper.getInstance(context);
        openConnecion();
        db.execSQL("DELETE FROM " + tableName);
    }

    public static void deleteTable(Context context, String tableName, String where) {
        DatabaseHelper.getInstance(context);
        openConnecion();
        db.execSQL("DELETE FROM " + tableName + " " + where);
    }

    public static long insertData(Context context, String table_name, ContentValues values) {
//        db = dbHelper.getWritableDatabase();
        DatabaseHelper.getInstance(context);
        db.beginTransaction();
        try {

            long result = db.insertOrThrow(table_name, null, values);
            Log.i(TAG, "Insert " + table_name + " successs");
            db.setTransactionSuccessful();
            return result;
        } catch (Exception e) {
            Log.e(TAG, "Error insert: " + e.getMessage());
            return -1;
        } finally {
            db.endTransaction();
        }

    }

    public static int insertData(Context context, String table_name, List<ContentValues> values) {
        DatabaseHelper.getInstance(context);
        db.beginTransaction();

        try {
            for (ContentValues value : values) {
                db.insertOrThrow(table_name, null, value);
            }
            Log.i(TAG, "2 Insert " + table_name + " successs");
            db.setTransactionSuccessful();
            return 1;
        } catch (Exception e) {
            Log.e(TAG, "Error insert: " + e.getMessage());
            return -1;
        } finally {
            db.endTransaction();
        }
    }

    public static int updateData(Context context, String table_name, ContentValues values, String where) {
        DatabaseHelper.getInstance(context);
        db.beginTransaction();
        try {
            int result = db.update(table_name, values, where, null);
            Log.i(TAG, "update " + table_name + " successs");
            db.setTransactionSuccessful();
            return result;
        } catch (Exception e) {
            Log.e(TAG, "Error updateData: " + e.getMessage());
            return -1;
        } finally {
            db.endTransaction();
        }
    }

    // be sure to call this method by: DatabaseHelper.getInstance.closeConnecion() when application is closed by    somemeans most likely
    // onDestroy method of application
    public synchronized void closeConnecion() {
        if (dbHelper != null) {
            dbHelper.close();
            db.close();
            dbHelper = null;
            db = null;
        }
    }


    public SQLiteDatabase getDB() {
        openConnecion();
        return db;
    }

}
