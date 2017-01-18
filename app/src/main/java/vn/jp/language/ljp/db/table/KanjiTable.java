package vn.jp.language.ljp.db.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by huynhtran on 5/12/16.
 */
public class KanjiTable {
    private static final String TAG = "KanjiTable";
    public static final String TABLE_NAME = "TblKanji";

    public static final String COL_NUM = "num";
    public static final String COL_KANJI = "kanji";
    public static final String COL_JP1 = "jp1";
    public static final String COL_JP2 = "jp2";
    public static final String COL_ROMAJI = "romaji";
    public static final String COL_IMG_PATH = "imgpath";
    public static final String COL_OT = "ot";
    public static final String COL_EX = "ex";
    public static final String CLEAR_TABLE = "delete from " + TABLE_NAME;

//    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
//            + COL_NUM + " INTEGER, "
//            + COL_LEVEL + " INTEGER, "
//            + COL_KANJI + " TEXT, "
//            + COL_JP1 + " TEXT,"
//            + COL_JP2 + " TEXT,"
//            + COL_IMG_PATH + " TEXT,"
//            + COL_OT + " TEXT,"
//            + COL_EX + " TEXT,"
//            + " );";

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
