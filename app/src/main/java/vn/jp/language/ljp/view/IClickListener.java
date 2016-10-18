package vn.jp.language.ljp.view;

import android.view.View;

/**
 * Created by Administrator on 10/18/2016.
 */

public interface IClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
