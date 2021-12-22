package vn.jp.language.ljp.view.dashboard;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.DashboardEntity;
import vn.jp.language.ljp.view.BaseAdapterView;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 10/17/2016.
 */

public class DashboardAdapter extends BaseAdapterView<BaseViewHolder> {

    private static String TAG = "DashboardAdapter";
    List<DashboardEntity> listData;

    public DashboardAdapter(List<DashboardEntity> listData) {
        this.listData = listData;
    }


    @Override
    protected int getItemLayout(int type) {
        if (type == TYPE_FOOTER)
            return R.layout.dashboard_footer_item1;
        else if (type == TYPE_FOOTER2)
            return R.layout.dashboard_footer_item2;
        else if (type == TYPE_ITEM)
            return R.layout.dashboard_item;
        return 0;
    }

    @Override
    protected BaseViewHolder getItemView(View view, int type) {
        if (type == TYPE_ITEM)
            return new DashboardHolder(view);
        else
            return new BaseViewHolder(view);

    }

    @Override
    protected List getListData() {
        return listData;
    }

    @Override
    public void onViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof DashboardHolder)
            ((DashboardHolder) holder).bind(listData.get(position - countHeaderLayout()));
    }

}
