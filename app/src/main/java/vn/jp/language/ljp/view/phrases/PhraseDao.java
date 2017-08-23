package vn.jp.language.ljp.view.phrases;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.BaseTable;
import vn.jp.language.ljp.db.table.PhraseTable;
import vn.jp.language.ljp.entity.PhraseEntity;
import vn.jp.language.ljp.utils.Log;

/**
 * Created by huynhtran on 5/12/16.
 */
public class PhraseDao extends BaseDao<PhraseEntity> {

    private static final String TAG = "PhraseDao";

    public PhraseDao(Context context) {
        super(context);
    }

    @Override
    public PhraseEntity fetch(Cursor cursor) {
        PhraseEntity entity = new PhraseEntity();
        entity.num = cursor.getInt(cursor.getColumnIndex(PhraseTable.COL_NUM));
        entity.jp = cursor.getString(cursor.getColumnIndex(PhraseTable.COL_JP));
        entity.romaji = cursor.getString(cursor.getColumnIndex(PhraseTable.COL_ROMAJI));
        entity.ot = cursor.getString(cursor.getColumnIndex(PhraseTable.COL_OT));
        entity.sound = cursor.getString(cursor.getColumnIndex(PhraseTable.COL_SOUND));
        return entity;
    }

    public List<PhraseEntity> getListData() {
        String sql = "SELECT * FROM " + PhraseTable.getTableName(lang)
                + " Where" + BaseTable.appendCond();

        Log.i(TAG, "phrase: sql=" + sql);
        return fetchAll(sql);
    }

    public static List<PhraseEntity> searchData(Context context, String text) {
        PhraseDao dao = new PhraseDao(context);

        String sql = "SELECT * FROM " + PhraseTable.getTableName(dao.lang)
                + " WHERE " + BaseTable.appendCond()
                + " And (" + PhraseTable.COL_ROMAJI + " like '%" + text + "%'"
                + " OR " + PhraseTable.COL_OT + " like '%" + text + "%')";
        Log.i(TAG, "searchData: sql:" + sql);
        return dao.fetchAll(sql);
    }

}
