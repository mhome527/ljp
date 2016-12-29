package vn.jp.language.ljp.view.grammar.detail;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.GrammarDetailEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseAdapter;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class GrammarDetailAdapter extends BaseAdapter<GrammarDetailHolder> {

    private static String TAG = "GrammarDetailAdapter";

    private List<GrammarDetailEntity> listData;

    public GrammarDetailAdapter(List<GrammarDetailEntity> listData) {
        Log.i(TAG, "GrammarDetailAdapter size:" + listData.size());
        this.listData = listData;
    }

    @Override
    public int getItemLayout() {
        return R.layout.grammar_detail_item;
    }


    @Override
    public GrammarDetailHolder onCreateView(View view) {
        return new GrammarDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(GrammarDetailHolder holder, int position) {
        GrammarDetailEntity entity = listData.get(position);
        holder.tvJp.setText(entity.getJp());
        holder.tvRomaji.setText(entity.getRomaji());
        holder.tvOt.setText(entity.getOt());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
