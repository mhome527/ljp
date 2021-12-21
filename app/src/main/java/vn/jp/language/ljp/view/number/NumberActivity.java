package vn.jp.language.ljp.view.number;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.viewpager.widget.ViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Utility;
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

    List<String> stringList;


    NumberPresenter presenter;
    NumberHeaderAdapter headerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.number_layout;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.title_counter));
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setHomeButtonEnabled(false); // disable the button
//            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
//            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
//            actionBar.setDisplayShowTitleEnabled(false); // remove title
////            toolbarTitle.setText(getString(R.string.title_kanji));
//
//        } else
//            Log.e(TAG, "initView actionBar NULL!!!!");

        presenter = new NumberPresenter(activity);
        stringList = presenter.getContentHeaderItem();

        final NumberPagerAdapter adapter = new NumberPagerAdapter
                (activity, getSupportFragmentManager(), stringList.size());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "curr page " + position);
                headerAdapter.currPos = position;
                headerAdapter.notifyDataSetChanged();

//                tvMean.setText(presenter.getNumberDescription(position));
//                gridView.smoothScrollToPosition(position);
                gridView.setSelection(position);
                setTitleCenter(stringList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        headerAdapter = new NumberHeaderAdapter(activity, presenter.getHeaderItem());
//        gridView.setNumColumns(stringList.size());
        gridView.setNumColumns(presenter.getHeaderItem().size());

        gridView.setAdapter(headerAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewPager.setCurrentItem(position);
            }
        });

        Utility.setWidthGrid(gridView);
    }

    @OnClick(R.id.imgPre)
    public void actionPre() {
        if (headerAdapter.currPos == 0) {
            return;
        }
        headerAdapter.currPos--;
        headerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(headerAdapter.currPos);
        gridView.setSelection(headerAdapter.currPos);
    }

    @OnClick(R.id.imgNext)
    public void actionNext() {
        if (headerAdapter.currPos == stringList.size()) {
            return;
        }
        headerAdapter.currPos++;
        headerAdapter.notifyDataSetChanged();
        viewPager.setCurrentItem(headerAdapter.currPos);
    }


}

