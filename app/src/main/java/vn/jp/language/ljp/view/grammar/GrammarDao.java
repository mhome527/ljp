package vn.jp.language.ljp.view.grammar;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.GrammarTable;
import vn.jp.language.ljp.entity.GrammarEntity;
import vn.jp.language.ljp.utils.Log;

/**
 * Created by huynhtran on 12/22/16.
 */
public class GrammarDao extends BaseDao<GrammarEntity> {

    private static final String TAG = "GrammarDao";

    public GrammarDao(Context context) {
        super(context);
    }

    @Override
    public GrammarEntity fetch(Cursor cursor) {
        String ex;
        GrammarEntity entity = new GrammarEntity();
        entity.setNum(cursor.getInt(cursor.getColumnIndex(GrammarTable.COL_NUM)));
        entity.setJp(cursor.getString(cursor.getColumnIndex(GrammarTable.COL_JP)));
        entity.setLevel(cursor.getInt(cursor.getColumnIndex(GrammarTable.COL_LEVEL)));
        entity.setRomaji(cursor.getString(cursor.getColumnIndex(GrammarTable.COL_ROMAJI)));
        entity.setMean(cursor.getString(cursor.getColumnIndex(GrammarTable.COL_MEAN)));
        ex = cursor.getString(cursor.getColumnIndex(GrammarTable.COL_EXAMPLE));
        if (ex == null || ex.trim().equals(""))
            if(cursor.getColumnIndex(GrammarTable.COL_EXAMPLE1) != -1)
            ex = cursor.getString(cursor.getColumnIndex(GrammarTable.COL_EXAMPLE1));
        entity.setExample(ex);
        entity.setFormation(cursor.getString(cursor.getColumnIndex(GrammarTable.COL_FORMATION)));
        return entity;
    }

//    public List<GrammarEntity> getListData() {
//        String sql = "SELECT "
//                + GrammarTable.COL_NUM
//                + GrammarTable.COL_JP
//                + GrammarTable.COL_ROMAJI
//                + GrammarTable.COL_MEAN
//                + GrammarTable.COL_FORMATION
//                + "'" + GrammarTable.COL_EXAMPLE + "'"
//                + " FROM " + GrammarTable.TABLE_NAME
//                + " ORDER BY " + GrammarTable.COL_NUM;
//        Log.i(TAG, "grammar: sql=" + sql);
//        return fetchAll(sql);
//    }

    /*
    select * from TblGrammar g1, TblGrammarVn g2
where g1.num = g2.num and g1.level = g2.level
and g1.level = 1
     */

    public List<GrammarEntity> getListData(int level) {
        String sql;
        if (lang.equals(Constant.VN)) {
            sql = "SELECT g1.num, g1.jp, g1.level, g1.romaji, g2.formation, g2.mean, g2.example, g1.example " + GrammarTable.COL_EXAMPLE1
                    + " FROM " + GrammarTable.TABLE_NAME + " g1, " + GrammarTable.TABLE_NAME_VN + " g2 "
                    + " WHERE g1." + GrammarTable.COL_NUM + " = " + " g2." + GrammarTable.COL_NUM
                    + " AND g1." + GrammarTable.COL_LEVEL + " = " + " g2." + GrammarTable.COL_LEVEL
                    + " AND g1." + GrammarTable.COL_LEVEL + " = " + level
                    + " ORDER BY g1." + GrammarTable.COL_NUM;
        } else {
            sql = "SELECT * FROM " + GrammarTable.TABLE_NAME
                    + " WHERE " + GrammarTable.COL_LEVEL + " = " + level
                    + " ORDER BY " + GrammarTable.COL_NUM;
        }

        Log.i(TAG, "grammar: sql=" + sql);
        return fetchAll(sql);
    }

    public static GrammarEntity getItemData(Context context, int level, int num) {
        String sql;
        GrammarDao dao = new GrammarDao(context);

        if (dao.lang.equals(Constant.VN)) {
            sql = "SELECT g1.num, g1.jp, g1.level, g1.romaji, g2.formation, g2.mean, g2.example, g1.example " + GrammarTable.COL_EXAMPLE1
                    + " FROM " + GrammarTable.TABLE_NAME + " g1, " + GrammarTable.TABLE_NAME_VN + " g2 "
                    + " WHERE g1." + GrammarTable.COL_NUM + " = " + " g2." + GrammarTable.COL_NUM
                    + " AND g1." + GrammarTable.COL_LEVEL + " = " + " g2." + GrammarTable.COL_LEVEL
                    + " AND g1." + GrammarTable.COL_LEVEL + " = " + level
                    + " AND g1." + GrammarTable.COL_NUM + " = " + num
                    + " ORDER BY " + GrammarTable.COL_JP;
        } else {
            sql = "SELECT * FROM " + GrammarTable.TABLE_NAME
                    + " WHERE " + GrammarTable.COL_LEVEL + " = " + level
                    + " AND " + GrammarTable.COL_NUM + " = " + num
                    + " ORDER BY " + GrammarTable.COL_JP;
        }

//        Log.i(TAG, "grammar: sql=" + sql);
        return dao.fetch(sql);
    }

    public static List<GrammarEntity> searchData(Context context, String text) {
        String sql;
        GrammarDao dao = new GrammarDao(context);

        if (dao.lang.equals(Constant.VN)) {
            sql = "SELECT g1.num, g1.jp, g1.level, g1.romaji, g2.formation, g2.mean, g2.example, g1.example " + GrammarTable.COL_EXAMPLE1
                    + " FROM " + GrammarTable.TABLE_NAME + " g1, " + GrammarTable.TABLE_NAME_VN + " g2 "
                    + " WHERE g1." + GrammarTable.COL_NUM + " = " + " g2." + GrammarTable.COL_NUM
                    + " AND g1." + GrammarTable.COL_LEVEL + " = " + " g2." + GrammarTable.COL_LEVEL
                    + " AND (g1." + GrammarTable.COL_JP + " like '%" + text + "%'"
                    + " OR Replace(g1." + GrammarTable.COL_ROMAJI + " , ' ', '') like '%" + text + "%')"
                    + " ORDER BY " + GrammarTable.COL_JP;
        } else {
            sql = "SELECT * FROM " + GrammarTable.TABLE_NAME
                    + " WHERE " + GrammarTable.COL_JP + " like '%" + text + "%'"
                    + " OR Replace(" + GrammarTable.COL_ROMAJI + " , ' ', '') like '%" + text + "%'"
                    + " ORDER BY " + GrammarTable.COL_JP;
//                + " OR " + GrammarTable.COL_MEAN + " like '%" + text + "%'";
        }
        Log.i(TAG, "grammar: sql=" + sql);
        return dao.fetchAll(sql);
    }


}
