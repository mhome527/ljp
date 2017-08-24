package vn.jp.language.ljp.view.food;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.sound.AudioPlayerManager;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.RecyclerTouchListener;

/**
 * Created by huynhtd on 10/17/2016.
 */

public class FoodActivity extends BaseActivity<FoodActivity> {

    private final String TAG = "FoodActivity";
    private final String FOLDER = "food/";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<WordEntity> listData;

    FoodAdapter adapter;
    FoodPresenter presenter;
    AudioPlayerManager audio;


    @Override
    protected int getLayout() {
        return R.layout.food_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "onCreate");

        presenter = new FoodPresenter(this);
        adapter = new FoodAdapter();
        audio = new AudioPlayerManager(this);

        setTitle(getString(R.string.title_food));
        initControl();
        loadData();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initControl() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //Add event
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new IClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i(TAG, "onClick row pos:" + listData.get(position).sound);
                audio.play(FOLDER + listData.get(position).sound);

            }

            @Override
            public void onLongClick(View view, int position) {
                Log.i(TAG, "onLongClick row pos:" + listData.get(position).romaji);
            }
        }));
    }

    private void loadData() {
        presenter.loadData(new ICallback<List<WordEntity>>() {
            @Override
            public void onCallback(List list) {
                listData = list;
                adapter.setData(listData, "");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail!!!!!!" + err);
            }
        });
    }


}
