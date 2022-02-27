package vn.jp.language.ljp.view.jlpt.listening;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.JlptMstEntity;
import vn.jp.language.ljp.view.BaseViewHolder;
import vn.jp.language.ljp.view.IJlptClickListener;

/**
 * Created by Administrator on 7/10/2017.
 */

public class JlptListHolder extends BaseViewHolder {

    @BindView(R.id.tvTextDate)
    TextView tvTextDate;

    @BindView(R.id.tvSoon)
    TextView tvSoon;

    @BindView(R.id.llMondai)
    View llMondai;

    @BindView(R.id.btnMondai5)
    Button btnMondai5;

    @BindView(R.id.btnLock2)
    Button btnLock2;

    @BindView(R.id.btnLock3)
    Button btnLock3;

    @BindView(R.id.btnLock4)
    Button btnLock4;

    @BindView(R.id.btnLock5)
    Button btnLock5;

    IJlptClickListener iJlptClickListener;

    public JlptListHolder(View itemView, IJlptClickListener iJlptClickListener) {
        super(itemView);
        this.iJlptClickListener = iJlptClickListener;
    }

    public void bind(JlptMstEntity item, boolean isPurchased) {
        tvTextDate.setText(item.test_date);
        if (item.isInserted == 1) {
            tvTextDate.setTextColor(Color.BLUE);
            tvSoon.setVisibility(View.GONE);
            llMondai.setVisibility(View.VISIBLE);
        } else {
            tvTextDate.setTextColor(Color.GRAY);
            tvSoon.setVisibility(View.VISIBLE);
            llMondai.setVisibility(View.GONE);
        }
        if (item.level >= 4) {
            btnMondai5.setVisibility(View.GONE);
        } else {
            btnMondai5.setVisibility(View.VISIBLE);
        }

        showHideButton(isPurchased);
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

    private void showHideButton(boolean isPurchased) {
        if (!isPurchased) {
            btnLock2.setVisibility(View.VISIBLE);
            btnLock3.setVisibility(View.VISIBLE);
            btnLock4.setVisibility(View.VISIBLE);
            btnLock5.setVisibility(View.VISIBLE);
        } else {
            btnLock2.setVisibility(View.GONE);
            btnLock3.setVisibility(View.GONE);
            btnLock4.setVisibility(View.GONE);
            btnLock5.setVisibility(View.GONE);

        }
    }
}
