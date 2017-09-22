package vn.jp.language.ljp.view.ono;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.OnoEntity;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 7/10/2017.
 */

public class OnoHolder extends BaseViewHolder {

    @BindView(R.id.tvJp)
    TextView tvJp;

    @BindView(R.id.tvRomaji)
    TextView tvRomaji;

    @BindView(R.id.tvMean)
    TextView tvMean;


    public OnoHolder(View itemView) {
        super(itemView);
    }

    public void bind(OnoEntity item) {

        tvJp.setText(item.getJp() + "");
        tvRomaji.setText(item.getRomaji() + "");
        tvMean.setText(item.getOt() + "");

    }
}
