package vn.jp.language.ljp.view.grammar;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.GrammarEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseAdapter;
import vn.jp.language.ljp.view.IClickListener;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class GrammarAdapter extends BaseAdapter<GrammarHolder> {

    private static String TAG = "GrammarAdapter";

    private List<GrammarEntity> listData;
    private IClickListener iClickListener;

    public GrammarAdapter(List<GrammarEntity> listData, IClickListener iClickListener) {
        Log.i(TAG, "GrammarAdapter size:" + listData.size());
        this.listData = listData;
        this.iClickListener = iClickListener;
    }

    @Override
    public int getItemLayout() {
        return R.layout.grammar_item;
    }


    @Override
    public GrammarHolder onCreateView(View view) {

        return new GrammarHolder(view, iClickListener);
    }

    @Override
    public void onBindViewHolder(GrammarHolder holder, int position) {
        GrammarEntity entity = listData.get(position);
        holder.tvGrammar.setText(entity.getJp());
        holder.tvMean.setText(entity.getMean());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public GrammarEntity getItem(int pos) {
        return listData.get(pos);
    }

}
