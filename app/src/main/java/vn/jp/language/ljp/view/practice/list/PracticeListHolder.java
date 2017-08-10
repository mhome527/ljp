package vn.jp.language.ljp.view.practice.list;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.BaseApplication;
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
            if (isPurchased || item.getNum() <= Constant.TRIAL_READING )
                imgLike.setVisibility(View.GONE);
            else
                imgLike.setVisibility(View.VISIBLE);
            tvContent.setMaxLines(2);

        } else if (item.getKind() == PracticeTable.TYPE_LISTENING) {
            if (isPurchased || item.getNum() <= Constant.TRIAL_LISTENING)
                imgLike.setVisibility(View.GONE);
            else
                imgLike.setVisibility(View.VISIBLE);
            tvContent.setMaxLines(1);

        } else {
            if (isPurchased || item.getNum() <= Constant.TRIAL_GRAMMAR || item.getKind() == PracticeTable.TYPE_KANJI)
                imgLike.setVisibility(View.GONE);
            else
                imgLike.setVisibility(View.VISIBLE);
            tvContent.setMaxLines(2);
        }

        tvNum.setText(item.getNum() + "");

//        tvContent.setText(item.getQuestion());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvContent.setText(Html.fromHtml(item.getQuestion(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvContent.setText(Html.fromHtml(item.getQuestion()));
        }

        if (item.getReview() == -1) //answer wrong
            tvContent.setTextColor(BaseApplication.getInstance().getResources().getColor(R.color.red));
        else if (item.getReview() == 1)
            tvContent.setTextColor(BaseApplication.getInstance().getResources().getColor(R.color.gray));
        else
            tvContent.setTextColor(BaseApplication.getInstance().getResources().getColor(R.color.black));

    }
}
