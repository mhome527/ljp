package vn.jp.language.ljp.db.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by huynhtran on 5/12/16.
 */
public class JlptListeningTable {
    private static final String TAG = "KanjiTable";
    public static final String TABLE_NAME = "Tbl_JLPT_Listening_master";

    public static final String COL_LEVEL = "Level";
    public static final String COL_TEST_DATE = "Test_date";
    public static final String COL_MONDAI = "Mondai";
    public static final String COL_TITLE = "Title";
    public static final String COL_FILENAME = "Filename";
    public static final String COL_LINK_DOWNLOAD = "Link_Download";
    public static final String CLEAR_TABLE = "delete from " + TABLE_NAME;


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
