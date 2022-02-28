package vn.jp.language.ljp.view.jlpt.grammar;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.JlptMstEntity;
import vn.jp.language.ljp.view.BaseViewHolder;
import vn.jp.language.ljp.view.IJlptClickListener;

/**
 * Created by Administrator on 7/10/2017.
 */

public class JlptGrammarListHolder extends BaseViewHolder {

    @BindView(R.id.tvTextDate)
    TextView tvTextDate;

    @BindView(R.id.tvSoon)
    TextView tvSoon;

    IJlptClickListener iJlptClickListener;
    JlptMstEntity item;

    public JlptGrammarListHolder(View itemView, IJlptClickListener iJlptClickListener) {
        super(itemView);
        this.iJlptClickListener = iJlptClickListener;
//        itemView.setOnClickListener(v -> iJlptClickListener.onClick(getLayoutPosition(), 0));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.isInserted == 1)
                    iJlptClickListener.onClick(getLayoutPosition(), 0);
            }
        });
    }

    public void bind(JlptMstEntity item) {
        this.item = item;
        if (item.isInserted == 1) {
            tvTextDate.setText(item.test_date);
            tvTextDate.setTextColor(Color.BLUE);
            tvSoon.setVisibility(View.GONE);

        } else {
            tvTextDate.setText(item.test_date);
            tvTextDate.setTextColor(Color.GRAY);
            tvSoon.setVisibility(View.VISIBLE);

        }
    }

}
