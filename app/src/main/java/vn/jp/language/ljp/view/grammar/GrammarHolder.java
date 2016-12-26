package vn.jp.language.ljp.view.grammar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.IClickListener;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class GrammarHolder extends RecyclerView.ViewHolder {
    TextView tvGrammar;
    TextView tvMean;

    public GrammarHolder(final View itemView, final IClickListener iClickListener) {
        super(itemView);
        tvGrammar = (TextView) itemView.findViewById(R.id.tvGrammar);
        tvMean = (TextView) itemView.findViewById(R.id.tvMean);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListener.onClick(v, getAdapterPosition());
            }
        });
    }
}
