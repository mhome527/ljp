package vn.jp.language.ljp.view.custom;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


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

    }
}
