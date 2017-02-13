package vn.jp.language.ljp.view.words;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.jp.language.ljp.BaseApplication;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.utils.Utility;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class WordItemHolder extends RecyclerView.ViewHolder {

    ImageView imgSound;
    ImageView imgWord;
    TextView tvJp;
    TextView tvRomaji;

    public WordItemHolder(final View itemView) {
        super(itemView);
        imgWord = (ImageView) itemView.findViewById(R.id.imgWord);
        imgSound = (ImageView) itemView.findViewById(R.id.imgSound);
        tvJp = (TextView) itemView.findViewById(R.id.tvJp);
        tvRomaji = (TextView) itemView.findViewById(R.id.tvRomaji);
    }

    public void bind(int position, boolean isPurchased, WordEntity entity) {
        if(isPurchased || position < Constant.TRIAL)
            imgSound.setImageResource(R.drawable.ic_speaker);
        else
            imgSound.setImageResource(R.drawable.ic_lock);

        tvJp.setText(entity.getJp1());
        tvRomaji.setText(entity.getRomaji());

//        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), "ic_back");
        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), entity.getImg());
        if (resourceId > 0) {
            imgWord.setImageResource(resourceId);
        }

    }

}
