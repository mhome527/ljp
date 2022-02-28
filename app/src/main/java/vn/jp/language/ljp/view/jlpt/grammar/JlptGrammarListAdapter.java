package vn.jp.language.ljp.view.jlpt.grammar;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.JlptMstEntity;
import vn.jp.language.ljp.view.BaseAdapterView;
import vn.jp.language.ljp.view.IJlptClickListener;

/**
 * Created by Administrator on 7/10/2017.
 */

public class JlptGrammarListAdapter extends BaseAdapterView<JlptGrammarListHolder> {

    List<JlptMstEntity> items;

    private IJlptClickListener iJlptClickListener;

    public JlptGrammarListAdapter(List<JlptMstEntity> items, IJlptClickListener iJlptClickListener) {
        this.items = items;
        this.iJlptClickListener = iJlptClickListener;
    }

    @Override
    protected int getHeaderLayout() {
        return 0;
    }

    @Override
    protected int getFooterLayout() {
        return 0;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.jlpt_grammar_list_item;
    }

    @Override
    protected JlptGrammarListHolder getHeaderView(View view) {
        return null;
    }

    @Override
    protected JlptGrammarListHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected JlptGrammarListHolder getItemView(View view) {
        return new JlptGrammarListHolder(view, iJlptClickListener);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    public void onViewHolder(JlptGrammarListHolder holder, int position) {
        holder.bind(items.get(position));
    }

}
