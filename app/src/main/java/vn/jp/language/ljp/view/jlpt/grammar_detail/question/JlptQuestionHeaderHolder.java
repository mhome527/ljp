package vn.jp.language.ljp.view.jlpt.grammar_detail.question;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 7/10/2017.
 */

public class JlptQuestionHeaderHolder extends BaseViewHolder {

    @BindView(R.id.tvArticle)
    WebView tvArticle;

    @BindView(R.id.imgTitle)
    ImageView imgTitle;

    public JlptQuestionHeaderHolder(View itemView) {
        super(itemView);
        WebSettings webSettings = tvArticle.getSettings();
        webSettings.setDefaultFontSize(14);

    }

    public void bind(String article, String imgBase64) {
        if(article == null || article.equals("")){
            tvArticle.setVisibility(View.GONE);
        }else{
            tvArticle.setVisibility(View.VISIBLE);
        }

        if (imgBase64 != null && !imgBase64.equals("")) {
            imgTitle.setVisibility(View.VISIBLE);
            imgTitle.setImageBitmap(Common.getImage(imgBase64));
        } else {
            imgTitle.setVisibility(View.GONE);
        }
//        tvArticle.setText(Html.fromHtml(article, Html.FROM_HTML_MODE_LEGACY));

        tvArticle.loadData(article, "text/html", "UTF-8");

    }
}
