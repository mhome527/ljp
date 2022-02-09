package vn.jp.language.ljp.view.jlpt.grammar_detail.question;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.JlptGrammarDetailEntity;
import vn.jp.language.ljp.view.BaseAdapterView;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 7/10/2017.
 */

public class JlptQuestionAdapter extends BaseAdapterView<BaseViewHolder> {

    List<JlptGrammarDetailEntity> items;
    boolean isPurchased = false;
    String article;


    public JlptQuestionAdapter(List<JlptGrammarDetailEntity> items, String article) {
        this.items = items;
        this.article = article;
    }

    @Override
    protected int getHeaderLayout() {
        return R.layout.jlpt_question_header_item;
    }

    @Override
    protected int getFooterLayout() {
        return 0;
    }

    @Override
    protected int getItemLayout() {
//        return R.layout.jlpt_question_item;
        return R.layout.jlpt_grammar_question;
    }

    @Override
    protected JlptQuestionHeaderHolder getHeaderView(View view) {
        return new JlptQuestionHeaderHolder(view);
    }

    @Override
    protected JlptQuestionHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected JlptQuestionHolder getItemView(View view) {
        return new JlptQuestionHolder(view);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    protected void onViewHolder(BaseViewHolder holder, int position) {
        if (holder instanceof JlptQuestionHeaderHolder) {
            ((JlptQuestionHeaderHolder) holder).bind(article);
        } else {
            ((JlptQuestionHolder) holder).bind(items.get(position));
        }
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }
}
