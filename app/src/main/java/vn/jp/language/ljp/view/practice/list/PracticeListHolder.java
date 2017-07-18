package vn.jp.language.ljp.view.practice.list;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
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
        if (item.getKind() == PracticeTable.TYPE_READING) {
            if (isPurchased || item.getBookmarks() < Constant.TRIAL_READING)
                imgLike.setVisibility(View.GONE);
            else
                imgLike.setVisibility(View.VISIBLE);
            tvNum.setText(item.getBookmarks() + "");

        } else if (item.getKind() == PracticeTable.TYPE_LISTENING) {
            if (isPurchased || item.getBookmarks() < Constant.TRIAL_LISTENING)
                imgLike.setVisibility(View.GONE);
            else
                imgLike.setVisibility(View.VISIBLE);
            tvNum.setText(item.getBookmarks() + "");


        } else {
            if (isPurchased || item.getNum() < Constant.TRIAL_GRAMMAR)
                imgLike.setVisibility(View.GONE);
            else
                imgLike.setVisibility(View.VISIBLE);
            tvNum.setText(item.getNum() + "");
        }

//        tvContent.setText(item.getQuestion());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvContent.setText(Html.fromHtml(item.getQuestion2(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvContent.setText(Html.fromHtml(item.getQuestion2()));
        }
    }
}
