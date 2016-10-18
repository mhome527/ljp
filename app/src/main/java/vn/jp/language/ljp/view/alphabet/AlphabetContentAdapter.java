package vn.jp.language.ljp.view.alphabet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.AlphabetEntity;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class AlphabetContentAdapter extends BaseAdapter {

    private static String TAG = "AlphabetContentAdapter";

    Context context;
    List<AlphabetEntity> listData;
    LayoutInflater layoutinflater;

    public AlphabetContentAdapter(Context context, List<AlphabetEntity> listData) {
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
            convertView = layoutinflater.inflate(R.layout.alphabet_item, parent, false);
            holderView.tv = (TextView) convertView.findViewById(R.id.tvContent);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }

        AlphabetEntity entity = listData.get(position);
//        holderView.img.setImageResource(entity.img);
        holderView.tv.setText(entity.content);
        return convertView;
    }

    static class HolderView {
        TextView tv;
    }
}
