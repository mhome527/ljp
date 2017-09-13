package vn.jp.language.ljp.view.practice.kanji;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.practice.list.IPracticeInterface;
import vn.jp.language.ljp.view.practice.reading.PracticeReadingAdapter;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeKanJiActivity extends BaseActivity<PracticeKanJiActivity> implements IPracticeInterface, ICallback<List<PracticeEntity>> {
    private final String TAG = "PracticeReadingActivity";

    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    @BindView(R.id.appBar)
    AppBarLayout appBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvNum)
    TextView tvNum;

    @BindView(R.id.imgBookmark)
    ImageButton imgBookmark;

    @BindView(R.id.imgNext)
    ImageButton imgNext;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    PracticeKanJiPresenter presenter;
    PracticeReadingAdapter adapter;
    List<PracticeEntity> items;

    String titleQ;

    int num;
    int idRef;
    int bookmark;

    @Override
    protected int getLayout() {
        return R.layout.practice_reading_layout;
    }

    @Override
    protected void initView() {
        level = getIntent().getIntExtra(Constant.INTENT_LEVEL, 0);
        idRef = getIntent().getIntExtra(Constant.INTENT_DETAIL_NUM, 0);
        num = getIntent().getIntExtra(Constant.INTENT_NUM, 0);
        bookmark = getIntent().getIntExtra(Constant.INTENT_BOOKMARK, 0);
        int v1 = getIntent().getIntExtra(Constant.INTENT_V1, 0);
        int v2 = getIntent().getIntExtra(Constant.INTENT_V2, 0);

        titleQ = getIntent().getStringExtra(Constant.INTENT_TITLE_Q);

        ///////////////
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(true); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(true); // remove the icon
            actionBar.setDisplayShowTitleEnabled(true); // remove title
//            toolbarTitle.setText(getString(R.string.title_alphabet));
            actionBar.setTitle(getString(R.string.title_n_kanji, v1, v2));
        }

        tvNum.setText(num + "");

        Common.setupRecyclerView(activity, recyclerView, null);
        presenter = new PracticeKanJiPresenter(activity, level, idRef);
        presenter.load(this);

        setBookmark();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                activity.setResult(AppCompatActivity.RESULT_OK);
                activity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.imgBookmark)
    public void actionBookmark() {
        bookmark = bookmark == 0 ? 1 : 0;
        setBookmark();
        presenter.updateBookmark(num, bookmark, 0);
    }

    @OnClick(R.id.imgNext)
    public void actionNext() {
        presenter.loadNext(++num, this);
    }


    // IPracticeInterface
    @Override
    public void onBookmark(int pos, int value) {
    }

    @Override
    public void onAns(int pos, int value) {
        Log.i(TAG, "onAns num, value" + items.get(pos).getNum() + "," + value);
        presenter.updateAns(items.get(pos).getNum(), value);
    }
//  end  IPracticeInterface

    //    ICallback
    @Override
    public void onCallback(List<PracticeEntity> data) {
        if (data == null || data.size() == 0) {
            --num;
            imgNext.setVisibility(View.INVISIBLE);
            return;
        }
        items = data;
        tvNum.setText(num + "");
        setBookmark();

        adapter = new PracticeReadingAdapter(activity, titleQ, data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFail(String err) {

    }
//  end  ICallback

    private void setBookmark() {
        if (bookmark == 0)
            imgBookmark.setImageResource(R.drawable.heart_off);
        else
            imgBookmark.setImageResource(R.drawable.heart_on);
    }
}
