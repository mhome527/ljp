package vn.jp.language.ljp.db.table;

/**
 * Created by Administrator on 8/23/2017.
 */

public class BaseTable {
    public static String appendCond(){
        return " t1.num = t2.num";
    }
}
