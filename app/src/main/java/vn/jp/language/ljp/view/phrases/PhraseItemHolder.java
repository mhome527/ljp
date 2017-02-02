package vn.jp.language.ljp.view.phrases;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PhraseEntity;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class PhraseItemHolder extends RecyclerView.ViewHolder {

    public TextView tvJp, tvRomaji, tvOt;
    public ImageButton imgSound;

    public PhraseItemHolder(View view) {
        super(view);
        tvJp = (TextView) view.findViewById(R.id.tvJp);
        tvRomaji = (TextView) view.findViewById(R.id.tvRomaji);
        tvOt = (TextView) view.findViewById(R.id.tvOt);
        imgSound = (ImageButton) view.findViewById(R.id.imgSound);
    }

    public void bind(int position, PhraseEntity entity){
        if(Constant.isPro || position < 4)
            imgSound.setImageResource(R.drawable.ic_speaker);
        else
            imgSound.setImageResource(R.drawable.ic_lock);

        tvJp.setText(entity.getJp());
        tvRomaji.setText(entity.getRomaji());
        tvOt.setText(entity.getOt());
    }

}
