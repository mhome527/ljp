package vn.jp.language.ljp.db.table;

/**
 * Created by huynhtran on 5/12/16.
 */
public class GrammarTable {
    private static final String TAG = "GrammarTable";
    public static final String TABLE_NAME = "tblGrammar";
    public static final String TABLE_NAME_VN = "tblGrammarVn";

    public static final String COL_NUM = "num";
    public static final String COL_LEVEL = "level";
    public static final String COL_JP = "jp";
    public static final String COL_ROMAJI = "romaji";
    public static final String COL_MEAN = "mean";
    public static final String COL_FORMATION = "formation";
    public static final String COL_EXAMPLE = "example";
    public static final String CLEAR_TABLE = "delete from " + TABLE_NAME;

//    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
//            + COL_NUM + " INTEGER, "
//            + COL_LEVEL + " INTEGER, "
//            + COL_JP + " TEXT, "
//            + COL_ROMAJI + " TEXT,"
//            + COL_MEAN + " TEXT,"
//            + COL_FORMATION + " TEXT,"
//            + COL_EXAMPLE + " TEXT,"
//            + " );";
//
//    public static void onCreate(SQLiteDatabase database) {
////        Log.i(TAG, "CREATE TABLE " + TABLE_NAME);
////        database.execSQL(CREATE_TABLE);
//    }
//
//    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
//                                 int newVersion) {
////        Log.i(TAG, "Upgrading database from version "
////                + oldVersion + " to " + newVersion
////                + ", which will destroy all old data");
////        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
////        onCreate(database);
//    }


}
