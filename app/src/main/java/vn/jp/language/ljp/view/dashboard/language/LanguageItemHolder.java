package vn.jp.language.ljp.view.dashboard.language;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.LanguageEntity;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by HuynhTD on 5/26/2017.
 */

public class LanguageItemHolder extends BaseViewHolder {


    @BindView(R.id.imgCheckEn)
    ImageView imgCheckEn;

    @BindView(R.id.imgLanguage)
    ImageView imgLanguage;

    @BindView(R.id.tvLanguage)
    TextView tvLanguage;

    public LanguageItemHolder(View itemView) {
        super(itemView);
    }

    public void setData(final LanguageEntity entity, String lang, final OnItemClickListener onItemClickListener) {
        if (entity.getLang().equals(lang))
            imgCheckEn.setBackgroundResource(R.drawable.bg_cycle_check_red);
        else
            imgCheckEn.setBackgroundResource(R.drawable.bg_cycle_un_check_red);

        tvLanguage.setText(entity.getText());
        imgLanguage.setBackgroundResource(entity.getRes());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(entity.getLang());
            }
        });

    }
}
