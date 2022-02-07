package vn.jp.language.ljp.db.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by huynhtran on 5/12/16.
 */
public class JlptGrammarDetailTable {
    private static final String TAG = "Tbl_JLPT_ALL_DETAIL";
    public static final String TABLE_NAME = "Tbl_JLPT_ALL_DETAIL";

    public static final String COL_LEVEL = "Level";
    public static final String COL_TEST_DATE = "Test_date";
    public static final String COL_QUESTION_ID = "Question_id";
    public static final String COL_NUM = "Num";
    public static final String COL_TITLE = "Title";
    public static final String COL_Q1 = "Q1";
    public static final String COL_Q2 = "Q2";
    public static final String COL_Q3 = "Q3";
    public static final String COL_Q4 = "Q4";
    public static final String COL_ANS = "Ans";
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
