package vn.jp.language.ljp.view.words;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.jp.language.ljp.BaseApplication;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.utils.Utility;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class WordItemHolder extends RecyclerView.ViewHolder {

    ImageView imgWord;
    TextView tvJp;
    TextView tvRomaji;

    public WordItemHolder(final View itemView) {
        super(itemView);
        imgWord = (ImageView) itemView.findViewById(R.id.imgWord);
        tvJp = (TextView) itemView.findViewById(R.id.tvJp);
        tvRomaji = (TextView) itemView.findViewById(R.id.tvRomaji);

    }

    public void bind(WordEntity entity) {
        tvJp.setText(entity.getJp1());
        tvRomaji.setText(entity.getRomaji());

//        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), "ic_back");
        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), entity.getImg());
        if (resourceId > 0) {
            imgWord.setImageResource(resourceId);
        }

    }

}
