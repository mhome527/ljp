package vn.jp.language.ljp.view.number;

import android.support.v4.view.ViewPager;
import android.widget.GridView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;

/**
 * Created by HuynhTran on 12/22/2016.
 */

public class NumberActivity extends BaseActivity<NumberActivity> {

    private final String TAG = "NumberActivity";

    @BindView(R.id.gridView)
    GridView gridView;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    NumberPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.number_layout;
    }

    @Override
    protected void initView() {

        presenter = new NumberPresenter(activity);
        final NumberPagerAdapter adapter = new NumberPagerAdapter
                (getSupportFragmentManager(), 5);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "curr page " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        NumberHeaderAdapter headerAdapter = new NumberHeaderAdapter(activity, presenter.getHeaderItem());
        gridView.setNumColumns(1);
        gridView.setAdapter(headerAdapter);
    }
}

