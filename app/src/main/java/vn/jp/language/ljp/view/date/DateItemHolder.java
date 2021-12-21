package vn.jp.language.ljp.view.date;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class DateItemHolder extends RecyclerView.ViewHolder {

    TextView tvKanji;
    TextView tvHiragana;
    TextView tvRomaji;
    TextView tvMean;

    public DateItemHolder(final View itemView) {
        super(itemView);
        tvKanji = (TextView) itemView.findViewById(R.id.tvKanji);
        tvHiragana = (TextView) itemView.findViewById(R.id.tvHiragana);
        tvRomaji = (TextView) itemView.findViewById(R.id.tvRomaji);
        tvMean = (TextView) itemView.findViewById(R.id.tvMean);
    }

    public void bind(WordEntity entity) {
        tvKanji.setText(entity.getJp1());
        tvHiragana.setText(entity.getJp2());
        tvRomaji.setText(entity.getRomaji());
        tvMean.setText(entity.getOt());
    }

}
