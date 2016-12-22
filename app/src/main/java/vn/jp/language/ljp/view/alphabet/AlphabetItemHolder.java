package vn.jp.language.ljp.view.alphabet;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.jp.language.ljp.R;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class AlphabetItemHolder extends RecyclerView.ViewHolder {
    TextView tvAlphabet;
    TextView tvMean;

    public AlphabetItemHolder(final View itemView) {
        super(itemView);
        tvAlphabet = (TextView) itemView.findViewById(R.id.tvAlphabet);
        tvMean = (TextView) itemView.findViewById(R.id.tvMean);
//        itemView.post(new Runnable() {
//            @Override
//            public void run() {
//                itemView.requestLayout();
//            }
//        });
    }
}
