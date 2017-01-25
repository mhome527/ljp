package vn.jp.language.ljp.view.date;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.view.BaseAdapter;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class DateContentAdapter extends BaseAdapter<DateItemHolder> {

    private static String TAG = "DateContentAdapter";

    List<WordEntity> listData;

    @Override
    public int getItemLayout() {
        return R.layout.date_item;
    }

    @Override
    public DateItemHolder onCreateView(View view) {
        return new DateItemHolder(view);
    }

    @Override
    public void onBindViewHolder(DateItemHolder holder, int position) {
        holder.bind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        if (listData == null)
            return 0;
        return listData.size();
    }

    public void setData(List<WordEntity> listData) {
        this.listData = listData;
    }

}
