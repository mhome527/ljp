package vn.jp.language.ljp.view.practice.list;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.view.BaseAdapterView;

/**
 * Created by Administrator on 7/10/2017.
 */

public class PracticeListAdapter extends BaseAdapterView<PracticeListHolder> {

    List<PracticeEntity> items;
    boolean isPurchased = false;

    public PracticeListAdapter(List<PracticeEntity> items) {
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
        return R.layout.practice_list_item;
    }

    @Override
    protected PracticeListHolder getHeaderView(View view) {
        return null;
    }

    @Override
    protected PracticeListHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected PracticeListHolder getItemView(View view) {
        return new PracticeListHolder(view);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    public void onBindViewHolder(PracticeListHolder holder, int position) {
        holder.bind(items.get(position), isPurchased);
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }
}
