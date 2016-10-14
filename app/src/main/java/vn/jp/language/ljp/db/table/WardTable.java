package vn.jp.language.ljp.db.table;

import android.database.sqlite.SQLiteDatabase;

import vn.jp.language.ljp.utils.Log;


/**
 * Created by huynhtran on 5/12/16.
 */
public class WardTable{

    private static final String TAG = "WardTable";
    public static final String TABLE_NAME = "Ward";

    public static final String COL_PROVINCE_ID = "ProvinceID";
    public static final String COL_WARD_CODE = "WardCode";
    public static final String COL_DISTRICT_ID = "DistrictID";
    public static final String COL_WARD_NAME = "WardName";
    public static final String CLEAR_TABLE = "delete from " + TABLE_NAME;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
            + COL_PROVINCE_ID + " INTEGER, "
            + COL_DISTRICT_ID + " INTEGER, "
            + COL_WARD_CODE + " TEXT, "
            + COL_WARD_NAME + " TEXT"
            + " );";

    public static void onCreate(SQLiteDatabase database) {
        Log.i(TAG, "CREATE TABLE " + TABLE_NAME);
        database.execSQL(CREATE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.i(TAG, "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }

}
