package vn.jp.language.ljp.view.ono;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.db.dao.BaseDao;
import vn.jp.language.ljp.db.table.OnoTable;
import vn.jp.language.ljp.entity.OnoEntity;
import vn.jp.language.ljp.utils.Log;

/**
 * Created by Administrator on 9/22/2017.
 */

public class OnoDao extends BaseDao<OnoEntity> {
    private static final String TAG = "OnoDao";


    public OnoDao(Context context) {
        super(context);
    }

    @Override
    protected OnoEntity fetch(Cursor cursor) {
        String ex, ex2, ot;

        OnoEntity entity = new OnoEntity();
        entity.setNum(cursor.getInt(cursor.getColumnIndex(OnoTable.COL_NUM)));
        entity.setJp(cursor.getString(cursor.getColumnIndex(OnoTable.COL_JP)));
        entity.setRomaji(cursor.getString(cursor.getColumnIndex(OnoTable.COL_ROMAJI)));
        entity.setBookmarks(cursor.getInt(cursor.getColumnIndex(OnoTable.COL_BOOKMARKS)));

        if (lang.equals(Constant.VN)) {
            ex = cursor.getString(cursor.getColumnIndex(OnoTable.COL_EX_VN));
            ex2 = cursor.getString(cursor.getColumnIndex(OnoTable.COL_EX_VN2));

            ot = cursor.getString(cursor.getColumnIndex(OnoTable.COL_OT_VN));

            if (ex == null || ex.equals("")) {
                ex = cursor.getString(cursor.getColumnIndex(OnoTable.COL_EX));
                ex2 = cursor.getString(cursor.getColumnIndex(OnoTable.COL_EX2));
            }
            if (ot == null || ot.equals(""))
                ot = cursor.getString(cursor.getColumnIndex(OnoTable.COL_OT));

        } else {
            ex = cursor.getString(cursor.getColumnIndex(OnoTable.COL_EX));
            ex2 = cursor.getString(cursor.getColumnIndex(OnoTable.COL_EX2));
            ot = cursor.getString(cursor.getColumnIndex(OnoTable.COL_OT));
        }

        entity.setEx(ex);
        entity.setEx2(ex2);
        entity.setOt(ot);


        return entity;
    }


    public List<OnoEntity> getItems() {

        String sql = "Select * From " + OnoTable.TABLE_NAME +
                " Order by " + OnoTable.COL_ROMAJI + " asc";

        return fetchAll(sql);
    }

    public List<OnoEntity> getBookmark() {
        String sql = "Select * From " + OnoTable.TABLE_NAME +
                " Where " + OnoTable.COL_BOOKMARKS + " = 1 " +
                " Order by " + OnoTable.COL_JP + " asc";

        return fetchAll(sql);
    }

    public List<OnoEntity> searchData(Context context, String text) {
        String sql;
        OnoDao dao = new OnoDao(context);

        sql = "SELECT * FROM " + OnoTable.TABLE_NAME
                + " WHERE " + OnoTable.COL_JP + " like '%" + text + "%'"
                + " OR  " + OnoTable.COL_ROMAJI + " like '%" + text + "%'"
                + " ORDER BY " + OnoTable.COL_ROMAJI;

        Log.i(TAG, "grammar: sql=" + sql);
        return dao.fetchAll(sql);
    }

    public void updateBookmark(int num, int bookmark) {
        ContentValues value = new ContentValues();
        value.put(OnoTable.COL_BOOKMARKS, bookmark);
        String where = OnoTable.COL_NUM + " = " + num;
        updateRow(OnoTable.TABLE_NAME, value, where);
    }

}
