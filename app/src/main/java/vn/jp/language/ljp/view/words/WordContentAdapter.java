package vn.jp.language.ljp.view.words;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.view.BaseAdapter;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class WordContentAdapter extends BaseAdapter<WordItemHolder> {

    private static String TAG = "WordContentAdapter";

    private List<WordEntity> listData;

    @Override
    public int getItemLayout() {
        return R.layout.word_item;
    }

    @Override
    public WordItemHolder onCreateView(View view) {
        return new WordItemHolder(view);
    }

    @Override
    public void onBindViewHolder(WordItemHolder holder, int position) {
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
