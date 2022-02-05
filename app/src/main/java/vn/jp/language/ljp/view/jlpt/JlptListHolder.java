package vn.jp.language.ljp.view.jlpt;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.JlptEntity;
import vn.jp.language.ljp.view.BaseViewHolder;
import vn.jp.language.ljp.view.IJlptClickListener;

/**
 * Created by Administrator on 7/10/2017.
 */

public class JlptListHolder extends BaseViewHolder {

    @BindView(R.id.tvTextDate)
    TextView tvTextDate;

    @BindView(R.id.btnMondai1)
    Button btnMondai1;

    IJlptClickListener iJlptClickListener;

    public JlptListHolder(View itemView, IJlptClickListener iJlptClickListener) {
        super(itemView);
        this.iJlptClickListener = iJlptClickListener;
    }

    public void bind(JlptEntity item, boolean isPurchased) {
        tvTextDate.setText(item.test_date);
    }

    @OnClick(R.id.btnMondai1)
    public void actionMondai1() {
        iJlptClickListener.onClick(getLayoutPosition(), 1);
    }

    @OnClick(R.id.btnMondai2)
    public void actionMondai2() {
        iJlptClickListener.onClick(getLayoutPosition(), 2);
    }

    @OnClick(R.id.btnMondai3)
    public void actionMondai3() {
        iJlptClickListener.onClick(getLayoutPosition(), 3);
    }

    @OnClick(R.id.btnMondai4)
    public void actionMondai4() {
        iJlptClickListener.onClick(getLayoutPosition(), 4);
    }

    @OnClick(R.id.btnMondai5)
    public void actionMondai5() {
        iJlptClickListener.onClick(getLayoutPosition(), 5);
    }

}
