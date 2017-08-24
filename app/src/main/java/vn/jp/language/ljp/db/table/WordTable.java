package vn.jp.language.ljp.db.table;

import android.database.sqlite.SQLiteDatabase;

import vn.jp.language.ljp.Constant;


/**
 * Created by huynhtran on 5/12/16.
 */
public class WordTable {

    private static final String TAG = "WordTable";
    private static final String TABLE_NAME = "tblJpWord";
//    public static final String TABLE_NAME_EN = "tblJpWordEn";

    public static final String COL_NUM = "num";
    public static final String COL_KIND = "kind";
    public static final String COL_JP1 = "jp1";
    public static final String COL_JP2 = "jp2";
    public static final String COL_OT = "ot";
    public static final String COL_OT_2 = "ot_temp";
    public static final String COL_IMG = "imgpath";
    public static final String COL_SOUND = "sound";
    public static final String COL_ROMAJI = "romaji";
//    public static final String CLEAR_TABLE = "delete from " + TABLE_NAME;

//    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
//            + COL_NUM + " INTEGER, "
//            + COL_KIND + " INTEGER, "
//            + COL_JP1 + " TEXT, "
//            + COL_JP2 + " TEXT, "
//            + COL_OT + " TEXT"
//            + COL_IMG + " TEXT"
//            + COL_SOUND + " TEXT"
//            + COL_ROMAJI + " TEXT"
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

    public static String getTableName(){
        return TABLE_NAME;
    }

    public static String getTableName(String lang) {
        String table;
        if (lang.equals(Constant.KO))
            table = TABLE_NAME + " t1, TblJpWordKo t2 ";
        else if (lang.equals(Constant.FR))
            table = TABLE_NAME + " t1, TblJpWordFr t2 ";
        else if (lang.equals(Constant.ZH))
            table = TABLE_NAME + " t1, TblJpWordCn t2 ";
        else if (lang.equals(Constant.ES))
            table = TABLE_NAME + " t1, TblJpWordEs t2 ";
        else
            table = TABLE_NAME + " t1, TblJpWordEn t2 ";
        return table;
    }

}
