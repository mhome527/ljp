package vn.jp.language.ljp.view.custom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.cardview.widget.CardView;

/**
 * Created by Administrator on 10/13/2016.
 */

public class CusCardView extends CardView {

    int squareDim = 1000000000;

    public CusCardView(Context context) {
        super(context);
    }

    public CusCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);


//        int h = this.getMeasuredHeight();
        int w = this.getMeasuredWidth();
//        int curSquareDim = Math.min(w, h);
//
//        if (curSquareDim < squareDim) {
//            squareDim = curSquareDim;
//        }

//        Log.d("MyApp", "h " + h + "w " + w + "squareDim " + squareDim);
//        setMeasuredDimension(squareDim, squareDim);

        setMeasuredDimension(w, w);
//        int count = getChildCount();
//        for (int i = 0; i < count; i++) {
//            final View child = getChildAt(i);
//            measureChild(child, w, w);
//        }

//        int totalWidth = 0;
//        int totalHeight = 0;
//        for (int i = 0; i < count; i++) {
//            final View child = getChildAt(i);
//            measureChild(child, w, heightMeasureSpec);
////            totalWidth += child.getMeasuredWidth();
//            if (child.getMeasuredHeight() > totalHeight) {
//                //height of the container, will be the largest height.
//                totalHeight = child.getMeasuredHeight();
//            }
//        }
//        setMeasuredDimension(w, w);
    }
}
