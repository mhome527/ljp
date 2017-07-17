package vn.jp.language.ljp.view.practice.reading;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.view.BaseAdapterView;
import vn.jp.language.ljp.view.BaseViewHolder;
import vn.jp.language.ljp.view.practice.list.IPracticeInterface;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeReadingAdapter extends BaseAdapterView<BaseViewHolder> {

    IPracticeInterface iPracticeInterface;
    String title;
    List<PracticeEntity> items;

    public PracticeReadingAdapter(IPracticeInterface iPracticeInterface, String title, List<PracticeEntity> items) {
        this.iPracticeInterface = iPracticeInterface;
        this.title = title;
        this.items = items;
    }

    @Override
    protected int getHeaderLayout() {
        return R.layout.practice_reading_header_item;
    }

    @Override
    protected int getFooterLayout() {
        return 0;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.practice_reading_item;
    }

    @Override
    protected PracticeReadingHeaderHolder getHeaderView(View view) {
        return new PracticeReadingHeaderHolder(view);
    }

    @Override
    protected PracticeReadingHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected PracticeReadingHolder getItemView(View view) {
        return new PracticeReadingHolder(view, iPracticeInterface);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    public void onViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof PracticeReadingHeaderHolder) {
            ((PracticeReadingHeaderHolder) holder).bind(title);
        } else {
            ((PracticeReadingHolder) holder).bind(position, items.get(position));
        }
    }
}
