package vn.jp.language.ljp.utils;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.RecyclerTouchListener;

/**
 * Created by Administrator on 1/25/2017.
 */

public class Common {
    public static void setupRecyclerView(Context context, RecyclerView recyclerView, IClickListener iClickListener) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Add event
        if (iClickListener != null)
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, iClickListener));

    }
}
