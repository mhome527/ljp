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
    protected int getItemLayout(int type) {
        if (type == TYPE_HEADER)
            return R.layout.practice_reading_header_item;
        else if (type == TYPE_ITEM)
            return R.layout.practice_reading_item;
        else
            return 0;
    }


    @Override
    protected BaseViewHolder getItemView(View view, int type) {
        if (type == TYPE_HEADER)
            return new PracticeReadingHeaderHolder(view);
        else if (type == TYPE_ITEM)
            return new PracticeReadingHolder(view, iPracticeInterface);
        else
            return new BaseViewHolder(view);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    public void onViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof PracticeReadingHeaderHolder) {
            ((PracticeReadingHeaderHolder) holder).bind(title);
        } else if (holder instanceof PracticeReadingHolder) {
            ((PracticeReadingHolder) holder).bind(position - countHeaderLayout(), items.get(position - countHeaderLayout()));
        }
    }
}
