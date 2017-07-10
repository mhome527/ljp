package vn.jp.language.ljp.db.table;

/**
 * Created by huynhtran on 5/12/16.
 */
public class PracticeTable {

    private static final String TAG = "PracticeTable";
    private static final String N1 = "TblPractice";
    private static final String N2 = "TblPracticeN2";
    private static final String N3 = "TblPracticeN3";
    private static final String N4 = "TblPracticeN4";
    private static final String N5 = "TblPracticeN5";

    public static final String COL_NUM = "num";
    public static final String COL_KIND = "kind";
    public static final String COL_BOOKMARKS = "bookmarks";
    public static final String COL_REVIEW = "review";
    public static final String COL_QUESTION = "question";
    public static final String COL_Q1 = "q1";
    public static final String COL_Q2 = "q2";
    public static final String COL_Q3 = "q3";
    public static final String COL_Q4 = "q4";
    public static final String COL_ANS = "ans";


    public static String getTableName(int level) {
        String table;
        switch (level) {
            case 4:
                table = PracticeTable.N4;
                break;
            case 3:
                table = PracticeTable.N3;
                break;
            case 2:
                table = PracticeTable.N2;
                break;
            case 1:
                table = PracticeTable.N1;
                break;
            default:
                table = PracticeTable.N5;
        }

        return table;
    }
}
