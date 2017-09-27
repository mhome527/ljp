package vn.jp.language.ljp.db.table;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by huynhtran on 5/12/16.
 */
public class OnoTable {

    private static final String TAG = "OnoTable";
    public static final String TABLE_NAME = "TblOno";
//    public static final String TABLE_NAME_EN = "tblJpWordEn";

    public static final String COL_NUM = "num";
    public static final String COL_JP = "jp";
    public static final String COL_ROMAJI = "romaji";
    public static final String COL_OT = "ot";
    public static final String COL_EX = "ex";
    public static final String COL_EX2 = "ex2";
    public static final String COL_BOOKMARKS = "bookmarks";
    public static final String COL_OT_VN = "ot_vn";
    public static final String COL_EX_VN = "ex_vn";
    public static final String COL_EX_VN2 = "ex_vn2";

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

    public static String getTableName() {
        return TABLE_NAME;
    }



}
