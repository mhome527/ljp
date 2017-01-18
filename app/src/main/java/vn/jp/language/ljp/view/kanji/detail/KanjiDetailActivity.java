package vn.jp.language.ljp.view.kanji.detail;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.KanjiEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by HuynhTD on 12/26/2016.
 */

public class KanjiDetailActivity extends BaseActivity<KanjiDetailActivity> {

    private static final String TAG = "GrammarDetailActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvBack)
    TextView tvBack;

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;


    @BindView(R.id.imgKanji)
    ImageView imgKanji;

    @BindView(R.id.tvOt)
    TextView tvOt;

    @BindView(R.id.tvJp1)
    TextView tvJp1;

    @BindView(R.id.tvJp2)
    TextView tvJp2;

    @BindView(R.id.tvExample)
    TextView tvExample;


    KanjiDetailPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.kanji_detail_layout;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
//        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
            actionBar.setDisplayShowTitleEnabled(false); // remove title

        } else
            Log.e(TAG, "initView actionBar NULL!!!!");

        presenter = new KanjiDetailPresenter(activity);

        int num = getIntent().getIntExtra(Constant.INTENT_DETAIL_NUM, 0);

        presenter.getData(num, new ICallback<KanjiEntity>() {
            @Override
            public void onCallback(final KanjiEntity data) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setData(data);
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "Error:" + err);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                activity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.tvBack)
    public void actionBack() {
        finish();
    }

    private void setData(KanjiEntity entity) {
//        toolbarTitle.setText(getString(R.string.title_kanji));
        toolbarTitle.setText(entity.getKanji());

        tvJp1.setText(entity.getJp1());
        tvJp2.setText(entity.getJp2());
        tvOt.setText(entity.getOt());
        tvExample.setText(entity.getExample());
    }
}
