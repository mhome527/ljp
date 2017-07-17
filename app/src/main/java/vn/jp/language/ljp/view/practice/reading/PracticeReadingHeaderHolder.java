package vn.jp.language.ljp.view.practice.reading;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeReadingHeaderHolder extends BaseViewHolder {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    public PracticeReadingHeaderHolder(View itemView) {
        super(itemView);
    }

    public void bind(String text) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvTitle.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvTitle.setText(Html.fromHtml(text));
        }
    }
}
