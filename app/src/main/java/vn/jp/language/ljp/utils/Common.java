package vn.jp.language.ljp.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.jp.language.ljp.R;
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

    public static void setupRecyclerViewGrid(Context context, RecyclerView recyclerView, IClickListener iClickListener) {
//        GridLayoutManager lLayout = new GridLayoutManager(context, 2);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(lLayout);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        //Add event
//        if (iClickListener != null)
//            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, iClickListener));
        setupRecyclerViewGrid(context, recyclerView, 2, iClickListener);
    }

    public static void setupRecyclerViewGrid(Context context, RecyclerView recyclerView, int column, IClickListener iClickListener) {
        GridLayoutManager lLayout = new GridLayoutManager(context, column);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Add event
        if (iClickListener != null)
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, iClickListener));
    }

    public static boolean isTablet(Context ctx) {
        return (ctx.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }
}
