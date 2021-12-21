package vn.jp.language.ljp.view.food;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import vn.jp.language.ljp.BaseApplication;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Utility;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class FoodItemHolder extends RecyclerView.ViewHolder {

    private final String TAG = "FoodItemHolder";
    ImageView imgWord;
    TextView tvJp;

    public FoodItemHolder(final View itemView) {
        super(itemView);
        imgWord = (ImageView) itemView.findViewById(R.id.imgWord);
        tvJp = (TextView) itemView.findViewById(R.id.tvJp);
    }

    public void bind(WordEntity entity) {

        tvJp.setText(entity.getJp1());

        String strImage = entity.getImg();
        Log.i(TAG, "bind filename:" + strImage);
        int resourceId = Utility.getResourcesID(BaseApplication.getInstance(), strImage);
        if (resourceId > 0) {
            imgWord.setImageResource(resourceId);
        }
    }
}
