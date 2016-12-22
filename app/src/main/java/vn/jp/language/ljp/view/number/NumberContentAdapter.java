package vn.jp.language.ljp.view.number;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.NumberEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseAdapter;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class NumberContentAdapter extends BaseAdapter<NumberItemHolder> {

    private static String TAG = "NumberContentAdapter";

    private List<NumberEntity> listData;

    public NumberContentAdapter(List<NumberEntity> listData) {
        Log.i(TAG, "NumberContentAdapter size:" + listData.size());
        this.listData = listData;
    }

    @Override
    public int getHeaderLayout() {
        return R.layout.number_header_item;
    }

    @Override
    public int getItemLayout() {
        return R.layout.number_item;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;

//        return super.getItemViewType(position);
    }

    @Override
    public NumberItemHolder onCreateView(View view) {
        return new NumberItemHolder(view);
    }

    @Override
    public void onBindViewHolder(NumberItemHolder holder, int position) {
        NumberEntity entity = listData.get(position);
        holder.tvNum.setText(entity.getNum() + "");
        holder.tvJp.setText(entity.getJp());
        holder.tvRomaji.setText(entity.getRomaji());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
