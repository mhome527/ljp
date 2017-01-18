package vn.jp.language.ljp.view.grammar.search;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.GrammarEntity;
import vn.jp.language.ljp.view.IClickListener;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class GrammarSearchHolder extends RecyclerView.ViewHolder {
    public TextView tvGrammar;
    public TextView tvRomaji;
    public TextView tvMean;

    public GrammarSearchHolder(final View itemView, final IClickListener iClickListener) {
        super(itemView);
        tvGrammar = (TextView) itemView.findViewById(R.id.tvGrammar);
        tvRomaji = (TextView) itemView.findViewById(R.id.tvRomaji);
        tvMean = (TextView) itemView.findViewById(R.id.tvMean);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListener.onClick(v, getAdapterPosition());
            }
        });
    }

    public void bind(GrammarEntity entity, String text) {
        String styledText ="";
        String textColor = "<font color='red'>" + text + "</font>";
//        String styledText = "This is <font color='red'>simple</font>.";
//        textView.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
        styledText = entity.getJp().replace(text, textColor);
        tvGrammar.setText(Html.fromHtml(styledText));

        styledText = entity.getRomaji().replace(text, textColor);
        tvRomaji.setText(Html.fromHtml(styledText));

        styledText = entity.getMean().replace(text, textColor);
        tvMean.setText(Html.fromHtml(styledText));
    }
}
