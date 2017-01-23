package vn.jp.language.ljp.view.kanji.detail;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
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


    private void setData(KanjiEntity entity) {

        setTitleCenter(entity.getKanji());

        tvJp1.setText(entity.getJp1());
        tvJp2.setText(entity.getJp2());
        tvOt.setText(entity.getOt());
        tvExample.setText(entity.getExample());

        Glide.with(activity)
                .load("file:///android_asset/butterfly.gif")
                .into(imgKanji);
    }

}
