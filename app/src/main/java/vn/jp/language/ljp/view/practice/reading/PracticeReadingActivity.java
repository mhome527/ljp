package vn.jp.language.ljp.view.practice.reading;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.practice.list.IPracticeInterface;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeReadingActivity extends BaseActivity<PracticeReadingActivity> implements IPracticeInterface, ICallback<List<PracticeEntity>> {
    private final String TAG = "PracticeReadingActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    PracticeReadingPresenter presenter;
    PracticeReadingAdapter adapter;
    List<PracticeEntity> items;

    String titleQ;

    @Override
    protected int getLayout() {
        return R.layout.practice_reading_layout;
    }

    @Override
    protected void initView() {
        int level = getIntent().getIntExtra(Constant.INTENT_LEVEL, 0);
        int idRef = getIntent().getIntExtra(Constant.INTENT_DETAIL_NUM, 0);
        titleQ = getIntent().getStringExtra(Constant.INTENT_TITLE_Q);

        Common.setupRecyclerView(activity, recyclerView, null);
        presenter = new PracticeReadingPresenter(activity, level, idRef);
        presenter.load(this);
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

    // IPracticeInterface
    @Override
    public void onBookmark(int pos, int value) {
    }

    @Override
    public void onAns(int pos, int value) {
        Log.i(TAG, "onAns pos, value" + pos + "," + value);
        presenter.updateAns(items.get(pos).getNum(), value);
    }
//  end  IPracticeInterface

    //    ICallback
    @Override
    public void onCallback(List<PracticeEntity> data) {
        items = data;
        adapter = new PracticeReadingAdapter(activity, titleQ, data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFail(String err) {

    }
//  end  ICallback
}
