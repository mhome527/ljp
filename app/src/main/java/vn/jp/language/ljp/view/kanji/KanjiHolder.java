package vn.jp.language.ljp.view.kanji;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.KanjiEntity;
import vn.jp.language.ljp.view.BaseViewHolder;
import vn.jp.language.ljp.view.IClickListener;

/**
 * Created by HuynhTD on 01/18/2017.
 */

public class KanjiHolder extends BaseViewHolder {

    @BindView(R.id.tvKanji)
    TextView tvKanji;

    @BindView(R.id.tvJp1)
    TextView tvJp1;

    @BindView(R.id.tvJp2)
    TextView tvJp2;

    @BindView(R.id.tvMean)
    TextView tvMean;

    public KanjiHolder(final View itemView, final IClickListener iClickListener) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListener.onClick(v, getAdapterPosition());
            }
        });
    }

    public void bind(KanjiEntity entity) {
        tvKanji.setText(entity.getKanji());
        tvJp1.setText(entity.getJp1());
        tvJp2.setText(entity.getJp2());
//        tvMean.setText(entity.getOt());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            tvMean.setText(Html.fromHtml(entity.getOt(), Html.FROM_HTML_MODE_LEGACY));
        else
            tvMean.setText(Html.fromHtml(entity.getOt()));

    }
}
