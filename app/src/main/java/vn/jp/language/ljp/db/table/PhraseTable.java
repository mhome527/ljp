package vn.jp.language.ljp.db.table;

import android.database.sqlite.SQLiteDatabase;

import vn.jp.language.ljp.Constant;

/**
 * Created by huynhtran on 5/12/16.
 */
public class PhraseTable {

    private static final String TAG = "PhraseTable";
    private static final String TABLE_NAME = "tblPhrase";
    public static final String TABLE_NAME_EN = "tblPhraseEn";

    public static final String COL_NUM = "num";
    public static final String COL_JP = "jp";
    public static final String COL_OT = "ot";
    public static final String COL_OT_2 = "ot_temp";
    public static final String COL_SOUND = "sound";
    public static final String COL_ROMAJI = "romaji";
    public static final String CLEAR_TABLE = "delete from " + TABLE_NAME;

//    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
//            + COL_NUM + " INTEGER, "
//            + COL_JP + " TEXT, "
//            + COL_OT + " TEXT"
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

    public static String getTableName(String lang) {
        String table;
       if (lang.equals(Constant.KO))
            table = TABLE_NAME + " t1, TblPhraseKo t2 ";
        else if (lang.equals(Constant.FR))
            table = TABLE_NAME + " t1, TblPhraseFr t2 ";
        else if (lang.equals(Constant.ZH))
            table = TABLE_NAME + " t1, TblPhraseCn t2 ";
        else if (lang.equals(Constant.ES))
            table = TABLE_NAME + " t1, TblPhraseEs t2 ";
        else if (lang.equals(Constant.VN))
            table = TABLE_NAME + " t1, TblPhraseVn t2 ";
        else
            table = TABLE_NAME + " t1, TblPhraseEn t2 ";
        return table;
    }


}
