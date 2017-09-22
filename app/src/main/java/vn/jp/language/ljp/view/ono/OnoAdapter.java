package vn.jp.language.ljp.view.ono;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.OnoEntity;
import vn.jp.language.ljp.view.BaseAdapterView;

/**
 * Created by Administrator on 7/10/2017.
 */

public class OnoAdapter extends BaseAdapterView<OnoHolder> {

    List<OnoEntity> items;

    public OnoAdapter(List<OnoEntity> items) {
        this.items = items;
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
        return R.layout.ono_item;
    }

    @Override
    protected OnoHolder getHeaderView(View view) {
        return null;
    }

    @Override
    protected OnoHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected OnoHolder getItemView(View view) {
        return new OnoHolder(view);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    public void onViewHolder(OnoHolder holder, int position) {
        holder.bind(items.get(position));
    }

}
