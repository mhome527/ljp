package vn.jp.language.ljp.view.practice.list;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.view.BaseAdapterView;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 7/10/2017.
 */

public class PracticeListAdapter extends BaseAdapterView<BaseViewHolder> {

    List<PracticeEntity> items;
    boolean isPurchased = false;

    public PracticeListAdapter(List<PracticeEntity> items) {
        this.items = items;
    }

    @Override
    protected int getItemLayout(int type) {
        if (type == TYPE_ITEM)
            return R.layout.practice_list_item;
        return 0;
    }

    @Override
    protected BaseViewHolder getItemView(View view, int type) {
        if (type == TYPE_ITEM)
            return new PracticeListHolder(view);
        else
            return new BaseViewHolder(view);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    public void onViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof PracticeListHolder)
            ((PracticeListHolder) holder).bind(items.get(position), isPurchased);
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }
}
