package vn.jp.language.ljp.view.ono;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.OnoEntity;
import vn.jp.language.ljp.view.BaseAdapterView;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 7/10/2017.
 */

public class OnoAdapter extends BaseAdapterView<BaseViewHolder> {

    List<OnoEntity> items;
    public String text = "";

    public OnoAdapter(List<OnoEntity> items) {
        this.items = items;
    }

    @Override
    protected int getItemLayout(int type) {
        if (type == TYPE_ITEM)
            return R.layout.ono_item;
        return 0;
    }

    @Override
    protected BaseViewHolder getItemView(View view, int type) {
        if (type == TYPE_ITEM)
            return new OnoHolder(view);
        else
            return new BaseViewHolder(view);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    public void onViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof OnoHolder) {
            if (text.equals(""))
                ((OnoHolder) holder).bind(items.get(position));
            else
                ((OnoHolder) holder).bind(items.get(position), text);
        }
    }

    //    =====================
    public void setData(List<OnoEntity> items, String text) {
        this.items = items;
        this.text = text;
    }

}
