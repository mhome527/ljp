package vn.jp.language.ljp.view.practice.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 7/10/2017.
 */

public class PracticeListHolder extends BaseViewHolder {

    @BindView(R.id.tvNum)
    TextView tvNum;

    @BindView(R.id.tvContent)
    TextView tvContent;


    @BindView(R.id.imgLike)
    ImageView imgLike;


    public PracticeListHolder(View itemView) {
        super(itemView);
    }

    public void bind(PracticeEntity item, boolean isPurchased) {
        if (isPurchased || item.getNum() < Constant.TRIAL_GRAMMAR) {
//            imgLike.setBackgroundResource(R.drawable.btn_star_off);
            imgLike.setVisibility(View.GONE);
        } else {
//            imgLike.setBackgroundResource(R.drawable.ic_lock);
            imgLike.setVisibility(View.VISIBLE);

        }

        tvNum.setText(item.getNum() + "");
        tvContent.setText(item.getQuestion());
    }
}
