package vn.jp.language.ljp.view.ono;

import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.OnoEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;

/**
 * Created by Administrator on 9/22/2017.
 */

public class OnoActivity extends BaseActivity<OnoActivity> implements IClickListener {

    private final String TAG = "OnoActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //    List<OnoEntity> items;
    OnoAdapter adapter;
    OnoPresenter presenter;


    @Override
    protected int getLayout() {
        return R.layout.practice_list_layout;
    }

    @Override
    protected void initView() {
        Common.setupRecyclerView(activity, recyclerView, this);
        presenter = new OnoPresenter(this);
        loadData();
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onLongClick(View view, int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_practice_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

//            case R.id.menu_search:
//                Intent iSearch = new Intent(activity, GrammarSearchActivity.class);
//                startActivity(iSearch);
//                return true;

//            case R.id.menuBookmark:
//                presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
//                Intent i = new Intent(activity, PracticeBookmarkActivity.class);
//                i.putExtra(Constant.INTENT_KIND, kind);
//                i.putExtra(Constant.INTENT_LEVEL, level);
//                i.putExtra(Constant.INTENT_V1, v1);
//                i.putExtra(Constant.INTENT_V2, v2);
//                startActivity(i);
//                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadData() {
        presenter.getItems(new ICallback<List<OnoEntity>>() {
            @Override
            public void onCallback(List<OnoEntity> data) {
//                items = data;
                adapter = new OnoAdapter(data);

                recyclerView.setAdapter(adapter);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();

                    }
                });

            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail err:" + err);
            }
        });
    }

}
