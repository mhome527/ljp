package vn.jp.language.ljp.db.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by huynhtran on 5/12/16.
 */
public class JlptGrammarTable {
    private static final String TAG = "Tbl_JLPT_ALL";
    public static final String TABLE_NAME = "Tbl_JLPT_ALL";

    public static final String COL_LEVEL = "Level";
    public static final String COL_TEST_DATE = "Test_date";
    public static final String COL_QUESTION_ID = "Question_id";
    public static final String COL_KIND = "Kind";
    public static final String COL_MONDAI = "Mondai";
    public static final String COL_ARTICLE = "Article";
    public static final String COL_IMAGE_T = "ImageT";
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
