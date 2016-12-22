package vn.jp.language.ljp.db.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by huynhtran on 5/12/16.
 */
public class NumberTable {

    private static final String TAG = "NumberTable";
    public static final String TABLE_NAME = "tblJpNumber";

    public static final String COL_KIND = "Kind";
    public static final String COL_NUM = "Num";
    public static final String COL_JP = "JP";
    public static final String COL_SOUND = "Sound";
    public static final String COL_ROMAJI = "Romaji";
    public static final String CLEAR_TABLE = "delete from " + TABLE_NAME;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
            + COL_KIND + " INTEGER, "
            + COL_NUM + " INTEGER, "
            + COL_JP + " TEXT, "
            + COL_SOUND + " TEXT,"
            + COL_ROMAJI + " TEXT"
            + " );";

    public static void onCreate(SQLiteDatabase database) {
//        Log.i(TAG, "CREATE TABLE " + TABLE_NAME);
//        database.execSQL(CREATE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
//        Log.i(TAG, "Upgrading database from version "
//                + oldVersion + " to " + newVersion
//                + ", which will destroy all old data");
//        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(database);
    }

}
