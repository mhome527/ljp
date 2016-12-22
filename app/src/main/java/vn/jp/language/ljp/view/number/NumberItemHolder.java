package vn.jp.language.ljp.view.number;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.jp.language.ljp.R;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class NumberItemHolder extends RecyclerView.ViewHolder {
    TextView tvNum;
    TextView tvJp;
    TextView tvRomaji;

    public NumberItemHolder(final View itemView) {
        super(itemView);
        tvNum = (TextView) itemView.findViewById(R.id.tvNum);
        tvJp = (TextView) itemView.findViewById(R.id.tvJp);
        tvRomaji = (TextView) itemView.findViewById(R.id.tvRomaji);
//        itemView.post(new Runnable() {
//            @Override
//            public void run() {
//                itemView.requestLayout();
//            }
//        });
    }
}
