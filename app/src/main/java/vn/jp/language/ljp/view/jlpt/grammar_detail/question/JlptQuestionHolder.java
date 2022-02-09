package vn.jp.language.ljp.view.jlpt.grammar_detail.question;

import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.JlptGrammarDetailEntity;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 2/07/2022.
 */

public class JlptQuestionHolder extends BaseViewHolder {

    @BindView(R.id.tvQuestion)
    WebView tvQuestion;
//    TextView tvQuestion;

    @BindView(R.id.imgQ1)
    ImageView imgQ1;

    @BindView(R.id.tvQ1)
    TextView tvQ1;

    @BindView(R.id.imgQ2)
    ImageView imgQ2;

    @BindView(R.id.tvQ2)
    TextView tvQ2;

    @BindView(R.id.imgQ3)
    ImageView imgQ3;

    @BindView(R.id.tvQ3)
    TextView tvQ3;

    @BindView(R.id.imgQ4)
    ImageView imgQ4;

    @BindView(R.id.tvQ4)
    TextView tvQ4;

    @BindView(R.id.llQ4)
    LinearLayout llQ4;

    //    IJlptClickListener iJlptClickListener;
    JlptGrammarDetailEntity item;

    int ansType = 0; //0: don't choice; 1: choice true; -1: choice wrong


    public JlptQuestionHolder(View itemView) {
        super(itemView);
//        this.iJlptClickListener = iJlptClickListener;
//        itemView.setOnClickListener(v -> iJlptClickListener.onClick(getLayoutPosition(), 0));
        WebSettings webSettings = tvQuestion.getSettings();
        webSettings.setDefaultFontSize(14);
    }

    public void bind(JlptGrammarDetailEntity item) {
        this.item = item;

//        tvQ1.setText(item.q1);
//        tvQ2.setText(item.q2);
//        tvQ3.setText(item.q3);
        tvQ1.setText(Html.fromHtml(item.q1, Html.FROM_HTML_MODE_LEGACY));
        tvQ2.setText(Html.fromHtml(item.q2, Html.FROM_HTML_MODE_LEGACY));
        tvQ3.setText(Html.fromHtml(item.q3, Html.FROM_HTML_MODE_LEGACY));

        if (item.q4 != null && !item.q4.equals(""))
            tvQ4.setText(Html.fromHtml(item.q4, Html.FROM_HTML_MODE_LEGACY));


        if (item.q4 != null && !item.q4.equals("")) {
            llQ4.setVisibility(View.VISIBLE);
        } else {
            llQ4.setVisibility(View.INVISIBLE);
        }

        tvQuestion.setVisibility(View.VISIBLE);
//        tvQuestion.setText(Html.fromHtml(item.num + "番　" + item.title, Html.FROM_HTML_MODE_LEGACY));
        tvQuestion.loadData(item.num + "番　" + item.title, "text/html", "UTF-8");

    }

    @OnClick(R.id.imgQ1)
    public void actionQ1() {
        setView(1, imgQ1);
    }

    @OnClick(R.id.tvQ1)
    public void actionTvQ1() {
        setView(1, imgQ1);
    }

    @OnClick(R.id.imgQ2)
    public void actionQ2() {
        setView(2, imgQ2);
    }

    @OnClick(R.id.tvQ2)
    public void actionTvQ2() {
        setView(2, imgQ2);
    }

    @OnClick(R.id.imgQ3)
    public void actionQ3() {
        setView(3, imgQ3);
    }

    @OnClick(R.id.tvQ3)
    public void actionTvQ3() {
        setView(3, imgQ3);
    }


    @OnClick(R.id.imgQ4)
    public void actionQ4() {
        setView(4, imgQ4);
    }

    @OnClick(R.id.tvQ4)
    public void actionTvQ4() {
        setView(4, imgQ4);
    }

    private void setView(int ans, ImageView img) {
        if (ans == item.ans) {
            img.setImageResource(R.drawable.circle_true);
            if (ansType == 0)
                ansType = 1;
        } else {
            img.setImageResource(R.drawable.circle_wrong);
            ansType = -1;
        }

//        activity.presenter.updateAns(item.getNum(), item.getRef(), ansType);
//        activity.setTitleQ(activity.presenter.countCorrect());
    }
}
