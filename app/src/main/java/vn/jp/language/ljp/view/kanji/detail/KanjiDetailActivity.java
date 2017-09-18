package vn.jp.language.ljp.view.kanji.detail;

import android.net.Uri;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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
    private final String PATH = "file:///android_asset/kanji/";

    @BindView(R.id.imgKanji)
    ImageView imgKanji;

    @BindView(R.id.tvJp1)
    TextView tvJp1;

    @BindView(R.id.tvMean)
    TextView tvMean;

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
        String fullPath;
        setTitleCenter(getString(R.string.title_kanji));

        tvJp1.setText(entity.getJp1());
        tvJp2.setText(entity.getJp2());
        tvExample.setText(entity.getExample());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            tvMean.setText(Html.fromHtml(entity.getOt(), Html.FROM_HTML_MODE_LEGACY));
        else
            tvMean.setText(Html.fromHtml(entity.getOt()));

        fullPath = PATH + entity.getImgPath() + ".gif";
        Log.i(TAG, "path:" + fullPath);

        RequestListener rq = new RequestListener<Uri, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Uri uri, Target<GlideDrawable> target, boolean b) {
                Log.e(TAG, "Error!!!!");
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable glideDrawable, Uri uri, Target<GlideDrawable> target, boolean b, boolean b1) {
                Log.i(TAG, "ready....");
                imgKanji.requestLayout();
                imgKanji.invalidate();
                return false;
            }
        };

        Glide.with(activity).load(Uri.parse(fullPath))
                .listener(rq)
                .dontTransform()
                .into(imgKanji);

    }

}
