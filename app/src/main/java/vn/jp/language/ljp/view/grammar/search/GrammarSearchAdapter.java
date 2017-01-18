package vn.jp.language.ljp.view.grammar.search;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.GrammarEntity;
import vn.jp.language.ljp.view.BaseAdapter;
import vn.jp.language.ljp.view.IClickListener;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class GrammarSearchAdapter extends BaseAdapter<GrammarSearchHolder> {

    private static String TAG = "GrammarSearchAdapter";

    private List<GrammarEntity> listData;
    private IClickListener iClickListener;
    public String text = "";

    public GrammarSearchAdapter(IClickListener iClickListener) {
        this.iClickListener = iClickListener;
    }

    @Override
    public int getItemLayout() {
        return R.layout.grammar_search_item;
    }


    @Override
    public GrammarSearchHolder onCreateView(View view) {
        return new GrammarSearchHolder(view, iClickListener);
    }

    @Override
    public void onBindViewHolder(GrammarSearchHolder holder, int position) {
        holder.bind(listData.get(position), text);
    }

    @Override
    public int getItemCount() {
        return (listData == null || listData.size() == 0) ? 0 : listData.size();
    }

    public GrammarEntity getItem(int pos) {
        return listData.get(pos);
    }

    public void setData(List<GrammarEntity> listData, String text) {
        this.listData = listData;
        this.text = text;
    }

    public void clearData() {
        if (listData != null)
            this.listData.clear();
    }
}
