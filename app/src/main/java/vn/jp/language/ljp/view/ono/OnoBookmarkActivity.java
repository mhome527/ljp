package vn.jp.language.ljp.view.ono;

import android.support.v7.widget.RecyclerView;
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

public class OnoBookmarkActivity extends BaseActivity<OnoBookmarkActivity> implements IClickListener {

    private final String TAG = "OnoBookmarkActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //    List<OnoEntity> items;
    OnoAdapter adapter;
    OnoPresenter presenter;

    List<OnoEntity> items;
    ICallback iCallback;

    @Override
    protected int getLayout() {
        return R.layout.practice_list_layout;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.title_ono));
        Common.setupRecyclerView(activity, recyclerView, this);
        presenter = new OnoPresenter(this);
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view, int position) {
        OnoEntity item = items.get(position);
        OnoDialog dialog = new OnoDialog(activity, item, new OnoInterface() {
            @Override
            public void onBookmark(OnoEntity item) {
                presenter.updateBookmark(item.getNum(), item.getBookmarks());
            }
        });
        dialog.show();
    }

    @Override
    public void onLongClick(View view, int position) {

    }


    private void loadData() {
        iCallback = new ICallback<List<OnoEntity>>() {
            @Override
            public void onCallback(List<OnoEntity> data) {
                items = data;
                adapter = new OnoAdapter(items);

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
        };

        presenter.getBookmark(iCallback);
    }

}
