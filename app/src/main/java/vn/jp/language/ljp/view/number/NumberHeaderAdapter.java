package vn.jp.language.ljp.view.number;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.jp.language.ljp.R;

/**
 * Created by Administrator on 12/22/2016.
 */

public class NumberHeaderAdapter extends BaseAdapter {

    private static String TAG = "NumberHeaderAdapter";

    Context context;
    List<String> listData;
    LayoutInflater layoutinflater;

    public NumberHeaderAdapter(Context context, List<String> listData) {
        this.context = context;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;

        if (convertView == null) {
            holderView = new HolderView();
            convertView = layoutinflater.inflate(R.layout.number_header_layout, parent, false);
            holderView.tvItem = (TextView) convertView.findViewById(R.id.tvItem);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }

        String name = listData.get(position);
        holderView.tvItem.setText(name);
        return convertView;
    }

    static class HolderView {
        TextView tvItem;
    }
}
