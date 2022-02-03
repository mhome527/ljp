package vn.jp.language.ljp.view.dashboard;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.DashboardEntity;
import vn.jp.language.ljp.view.BaseAdapterView;

/**
 * Created by Administrator on 10/17/2016.
 */

public class DashboardAdapter extends BaseAdapterView<DashboardHolder> {

    private static String TAG = "DashboardAdapter";
    List<DashboardEntity> listData;

    public DashboardAdapter(List<DashboardEntity> listData) {
        this.listData = listData;
    }

    @Override
    protected int getHeaderLayout() {
        return 0;
    }

    @Override
    protected int getFooterLayout() {
        return 0;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.dashboard_item;
    }

    @Override
    protected DashboardHolder getHeaderView(View view) {
        return null;
    }

    @Override
    protected DashboardHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected DashboardHolder getItemView(View view) {
        return new DashboardHolder(view);
    }

    @Override
    protected List getListData() {
        return listData;
    }

    @Override
    public void onViewHolder(DashboardHolder holder, int position) {
        holder.bind(listData.get(position));
    }

}
