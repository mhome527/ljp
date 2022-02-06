package vn.jp.language.ljp.view.jlpt.grammar;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.JlptGrammarEntity;
import vn.jp.language.ljp.view.BaseViewHolder;
import vn.jp.language.ljp.view.IJlptClickListener;

/**
 * Created by Administrator on 7/10/2017.
 */

public class JlptGrammarListHolder extends BaseViewHolder {

    @BindView(R.id.tvTextDate)
    TextView tvTextDate;

    IJlptClickListener iJlptClickListener;

    public JlptGrammarListHolder(View itemView, IJlptClickListener iJlptClickListener) {
        super(itemView);
        this.iJlptClickListener = iJlptClickListener;
        itemView.setOnClickListener(v -> iJlptClickListener.onClick(getLayoutPosition(), 0));
    }

    public void bind(JlptGrammarEntity item, boolean isPurchased) {
        tvTextDate.setText(item.test_date);
    }

}
