package vn.jp.language.ljp.view.words;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class WordsAdapter extends BaseAdapter {

    private static String TAG = "WordsAdapter";

    Context context;
    List<WordEntity> listData;
    LayoutInflater layoutinflater;

    public WordsAdapter(Context context, List<WordEntity> listData) {
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
            convertView = layoutinflater.inflate(R.layout.word_item, parent, false);
            holderView.img = (ImageButton) convertView.findViewById(R.id.imageButton);
            holderView.tv = (TextView) convertView.findViewById(R.id.tvContent);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }

        WordEntity entity = listData.get(position);
//        holderView.img.setImageResource(entity.img);
        holderView.tv.setText(entity.other);
        return convertView;
    }

    static class HolderView {
        ImageButton img;
        TextView tv;
    }
}
