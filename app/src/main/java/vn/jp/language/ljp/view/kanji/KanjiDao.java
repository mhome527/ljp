package vn.jp.language.ljp.view.kanji;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.BaseTable;
import vn.jp.language.ljp.db.table.KanjiTable;
import vn.jp.language.ljp.entity.KanjiEntity;
import vn.jp.language.ljp.utils.Log;

/**
 * Created by huynhtran on 01/18/17.
 */
public class KanjiDao extends BaseDao<KanjiEntity> {

    private static final String TAG = "KanjiDao";

    public KanjiDao(Context context) {
        super(context);
    }

    @Override
    public KanjiEntity fetch(Cursor cursor) {
        KanjiEntity entity = new KanjiEntity();
        entity.setNum(cursor.getInt(cursor.getColumnIndex(KanjiTable.COL_NUM)));
        entity.setKanji(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_KANJI)));
        entity.setJp1(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_JP1)));
        entity.setJp2(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_JP2)));
        entity.setRomaji(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_ROMAJI)));
        entity.setImgPath(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_IMG_PATH)));
        entity.setOt(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_OT)));
        entity.setExample(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_EX)));
        return entity;
    }

    public static KanjiEntity getData(Context context, int num) {
        KanjiDao dao = new KanjiDao(context);

        String sql = "SELECT * "
                + " FROM " + KanjiTable.getTableName(dao.lang)
                + " WHERE " + BaseTable.appendCond()
                + " And t1." + KanjiTable.COL_NUM + " =" + num;

        Log.i(TAG, "kanji: sql=" + sql);
        return dao.fetch(sql);
    }

    public static List<KanjiEntity> getListData(Context context) {
        KanjiDao dao = new KanjiDao(context);
        String sql = "SELECT * "
                + " FROM " + KanjiTable.getTableName(dao.lang)
                + " Where" + BaseTable.appendCond()
                + " ORDER BY " + KanjiTable.COL_NUM;
        Log.i(TAG, "kanji: sql=" + sql);
        return dao.fetchAll(sql);
    }


    public static List<KanjiEntity> searchData(Context context, String text) {
        KanjiDao dao = new KanjiDao(context);
        String sql = "SELECT * FROM " + KanjiTable.getTableName(dao.lang)
                + " WHERE " + BaseTable.appendCond()
                + " And (" + KanjiTable.COL_JP1 + " like '%" + text + "%'"
                + " OR " + KanjiTable.COL_JP2 + " like '%" + text + "%'"
                + " OR " + KanjiTable.COL_ROMAJI + " like '%" + text + "%'"
                + " OR " + KanjiTable.COL_OT + " like '%" + text + "%')";
        Log.i(TAG, "searchData: sql=" + sql);
        return dao.fetchAll(sql);
    }


}
