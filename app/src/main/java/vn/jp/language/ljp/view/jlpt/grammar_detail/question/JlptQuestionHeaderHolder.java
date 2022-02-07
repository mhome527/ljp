package vn.jp.language.ljp.view.jlpt.grammar_detail.question;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 7/10/2017.
 */

public class JlptQuestionHeaderHolder extends BaseViewHolder {

    @BindView(R.id.tvArticle)
    TextView tvArticle;


    public JlptQuestionHeaderHolder(View itemView) {
        super(itemView);
    }

    public void bind(String article) {
        if(article == null || article.equals("")){
            tvArticle.setVisibility(View.GONE);
        }else{
            tvArticle.setVisibility(View.VISIBLE);
        }
        tvArticle.setText(Html.fromHtml(article, Html.FROM_HTML_MODE_LEGACY));
    }
}
