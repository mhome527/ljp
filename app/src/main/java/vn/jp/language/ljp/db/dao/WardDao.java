package vn.jp.language.ljp.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import vn.jp.language.ljp.db.DatabaseHelper;
import vn.jp.language.ljp.db.table.WardTable;
import vn.jp.language.ljp.entity.WardEntity;
import vn.jp.language.ljp.utils.Log;

/**
 * Created by huynhtran on 5/12/16.
 */
public class WardDao extends BaseDao  {

    private final String TAG = "WardDao";

    public WardDao(Context context) {
        super(context);
    }

    @Override
    protected ContentValues getContentValues(Object entity) {
        return null;
    }


    @Override
    public WardEntity fetch(Cursor cursor) {
        WardEntity ward = new WardEntity();
//        ward.setProvinceID(cursor.getInt(cursor.getColumnIndex(WardTable.COL_PROVINCE_ID)));
//        ward.setDistrictID(cursor.getInt(cursor.getColumnIndex(WardTable.COL_DISTRICT_ID)));
//        ward.setWardCode(cursor.getString(cursor.getColumnIndex(WardTable.COL_WARD_CODE)));
//        ward.setWardName(cursor.getString(cursor.getColumnIndex(WardTable.COL_WARD_NAME)));
        return ward;
    }

    public List<WardEntity> get() {
        List<WardEntity> wards = new ArrayList<>();
        String sql = "SELECT * FROM " + WardTable.TABLE_NAME;
        Cursor cursor = null;
        try {
            cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            wards = fetchAll(cursor);
        } catch (Exception e) {
            Log.e(TAG, "getDistric error:" + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return wards;
    }

    public WardEntity getByID(int id) {
        WardEntity entity = null;
        String sql = "SELECT * FROM " + WardTable.TABLE_NAME + " WHERE " + WardTable.COL_DISTRICT_ID + " = " + id;
        Cursor cursor = null;
        try {
            cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    entity = fetch(cursor);
                    return entity;
                }
            }
            return entity;
        } catch (Exception e) {
            Log.e(TAG, "getDistric error:" + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return null;
    }

    public List<WardEntity> getListData(int id) {
        List<WardEntity> wards = new ArrayList<>();
        String sql = "SELECT * FROM " + WardTable.TABLE_NAME + " WHERE " + WardTable.COL_DISTRICT_ID + " = " + id
                + " ORDER BY " + WardTable.COL_WARD_NAME;
        Cursor cursor = null;
        try {
            cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            wards = fetchAll(cursor);
        } catch (Exception e) {
            Log.e(TAG, "getDistric error:" + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return wards;
    }


    public String getWardID(int id) {
        WardEntity entity;
        String sql = "SELECT * FROM " + WardTable.TABLE_NAME + " WHERE " + WardTable.COL_DISTRICT_ID + " = " + id;
        Cursor cursor = null;
        try {
            cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    entity = fetch(cursor);
                    return entity.getWardCode();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "getDistric error:" + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return "";
    }

    public String getWardName(String wardCode) {
        WardEntity entity;
        String sql = "SELECT * FROM " + WardTable.TABLE_NAME + " WHERE " + WardTable.COL_WARD_CODE + " = '" + wardCode + "'";
        Cursor cursor = null;
        try {
            cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    entity = fetch(cursor);
                    return entity.getWardName();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "getDistric error:" + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return "";
    }

    public int countWard() {
        String sql = "SELECT count(*) FROM " + WardTable.TABLE_NAME;
        Cursor cursor = null;
        try {
            cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    return cursor.getInt(0);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "getWardID error:" + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return 0;
    }

    public String getWardID(int districtId, String wardName) {
        WardEntity entity;
        String sql = "SELECT * FROM " + WardTable.TABLE_NAME + " WHERE " +
                " " + WardTable.COL_DISTRICT_ID + " = " + districtId +
                " AND " + WardTable.COL_WARD_NAME + " LIKE '%" + wardName.toLowerCase() + "%'";
        Log.i(TAG, "wardTable sql:" + sql);

        Cursor cursor = null;
        try {
            cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    entity = fetch(cursor);
                    Log.i(TAG, "getWardId2 wardId:" + entity.getWardCode());
                    return entity.getWardCode();
                }
            }else
                Log.i(TAG, "getWardId not found data");

        } catch (Exception e) {
            Log.e(TAG, "getWardID error:" + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return "";
    }

    public long insert(WardEntity ward) {
        return DatabaseHelper.insertData(context, WardTable.TABLE_NAME, getContentValues(ward));

    }

    public int insert(List<WardEntity> items) {
        List<ContentValues> lstContentValues = new ArrayList<>();

        for (WardEntity ward : items)
            lstContentValues.add(getContentValues(ward));

        return DatabaseHelper.insertData(context, WardTable.TABLE_NAME, lstContentValues);

    }

}
