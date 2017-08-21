package vn.jp.language.ljp.view.grammar;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

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
        GrammarEntity entity = new GrammarEntity();
        entity.setNum(cursor.getInt(cursor.getColumnIndex(GrammarTable.COL_NUM)));
        entity.setJp(cursor.getString(cursor.getColumnIndex(GrammarTable.COL_JP)));
        entity.setLevel(cursor.getInt(cursor.getColumnIndex(GrammarTable.COL_LEVEL)));
        entity.setRomaji(cursor.getString(cursor.getColumnIndex(GrammarTable.COL_ROMAJI)));
        entity.setMean(cursor.getString(cursor.getColumnIndex(GrammarTable.COL_MEAN)));
        entity.setExample(cursor.getString(cursor.getColumnIndex(GrammarTable.COL_EXAMPLE)));
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

    public List<GrammarEntity> getListData(int level) {
        String sql = "SELECT * FROM " + GrammarTable.TABLE_NAME
                + " WHERE " + GrammarTable.COL_LEVEL + " = " + level
                + " ORDER BY " + GrammarTable.COL_NUM;
        Log.i(TAG, "grammar: sql=" + sql);
        return fetchAll(sql);
    }

    public static GrammarEntity getItemData(Context context, int level, int num) {
        String sql = "SELECT * FROM " + GrammarTable.TABLE_NAME
                + " WHERE " + GrammarTable.COL_LEVEL + " = " + level
                + " AND " + GrammarTable.COL_NUM + " = " + num;
//        Log.i(TAG, "grammar: sql=" + sql);
        GrammarDao dao = new GrammarDao(context);
        return dao.fetch(sql);
    }

    public static List<GrammarEntity> searchData(Context context, String text) {
        String sql = "SELECT * FROM " + GrammarTable.TABLE_NAME
                + " WHERE " + GrammarTable.COL_JP + " like '%" + text + "%'"
                + " OR " + GrammarTable.COL_ROMAJI + " like '%" + text + "%'"
                + " OR " + GrammarTable.COL_MEAN + " like '%" + text + "%'";
        Log.i(TAG, "grammar: sql=" + sql);
        GrammarDao dao = new GrammarDao(context);
        return dao.fetchAll(sql);
    }


}
