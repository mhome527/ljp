package vn.jp.language.ljp.view.alphabet;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.AlphabetEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseAdapter;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class AlphabetContentAdapter extends BaseAdapter<AlphabetItemHolder> {

    private static String TAG = "AlphabetContentAdapter";

    private List<AlphabetEntity> listData;
    Constant.TYPE_ALPHABET alphabet;

    public AlphabetContentAdapter(Constant.TYPE_ALPHABET alphabet, List<AlphabetEntity> listData) {
        Log.i(TAG, "AlphabetContentAdapter size:" + listData.size());
        this.listData = listData;
        this.alphabet = alphabet;
    }

    @Override
    public int getItemLayout() {
        return R.layout.alphabet_item;
    }

    @Override
    public AlphabetItemHolder onCreateView(View view) {
        return new AlphabetItemHolder(view);
    }

    @Override
    public void onBindViewHolder(AlphabetItemHolder holder, int position) {
        AlphabetEntity entity = listData.get(position);
        if (entity.getOt().equals("-")) {
            holder.tvAlphabet.setText("");
            holder.tvMean.setText("");

        } else {
            if (alphabet.equals(Constant.TYPE_ALPHABET.HIRAGANA))
                holder.tvAlphabet.setText(entity.getJp1());
            else
                holder.tvAlphabet.setText(entity.getJp2());

            holder.tvMean.setText(entity.getOt());
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

}
