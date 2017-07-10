package vn.jp.language.ljp.view.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.DashboardEntity;
import vn.jp.language.ljp.view.BaseViewHolder;

/**
 * Created by Administrator on 7/10/2017.
 */

public class DashboardHolder extends BaseViewHolder {

    @BindView(R.id.imgItem)
    ImageView imgItem;

    @BindView(R.id.tvContent)
    TextView tvContent;

    public DashboardHolder(View itemView) {
        super(itemView);
    }

    public void bind(DashboardEntity item) {
        imgItem.setImageResource(item.img);
        tvContent.setText(item.text);
    }
}
