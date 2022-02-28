package vn.jp.language.ljp.view.jlpt.listening;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.JlptMstEntity;
import vn.jp.language.ljp.view.BaseAdapterView;
import vn.jp.language.ljp.view.IJlptClickListener;

/**
 * Created by Administrator on 7/10/2017.
 */

public class JlptListAdapter extends BaseAdapterView<JlptListHolder> {

    List<JlptMstEntity> items;
    boolean isPurchased = false;

    private IJlptClickListener iJlptClickListener;

    public JlptListAdapter(List<JlptMstEntity> items, boolean isPurchased , IJlptClickListener iJlptClickListener) {
        this.items = items;
        this.iJlptClickListener = iJlptClickListener;
        this.isPurchased = isPurchased;
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
        return R.layout.jlpt_listening_list_item;
    }

    @Override
    protected JlptListHolder getHeaderView(View view) {
        return null;
    }

    @Override
    protected JlptListHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected JlptListHolder getItemView(View view) {
        return new JlptListHolder(view, iJlptClickListener);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    public void onViewHolder(JlptListHolder holder, int position) {
        holder.bind(items.get(position), isPurchased);
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
        notifyDataSetChanged();
    }
}
