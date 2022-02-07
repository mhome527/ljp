package vn.jp.language.ljp.view.jlpt.listening_detail;

import static vn.jp.language.ljp.R.id.btnView;

import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.JlptListeningEntity;
import vn.jp.language.ljp.sound.AudioPlayerManager;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 7/18/2017.
 */

public class JlptListeningActivity extends BaseActivity<JlptListeningActivity> implements ICallback<List<JlptListeningEntity>> {
    private final String TAG = "PracticeListeningActivity";
    private final String FOLDER = "n/";

    @BindView(R.id.tvMondai)
    TextView tvMondai;

    @BindView(R.id.tvTitleQuestion)
    TextView tvTitleQuestion;

    @BindView(R.id.imgPre)
    ImageButton imgPre;

   @BindView(R.id.imgSpeak)
    ImageButton imgSpeak;

    @BindView(R.id.imgNext)
    ImageButton imgNext;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    JlptListeningAdapter adapter;

    AudioPlayerManager audio;
    List<JlptListeningEntity> items;

    int pos;
    JlptListeningPresenter presenter;

    String filename;

    @Override
    protected int getLayout() {
        return R.layout.jlpt_listening_main_layout;
    }

    @Override
    protected void initView() {

        int level = getIntent().getIntExtra(Constant.INTENT_LEVEL, 0);
        String test_date = getIntent().getStringExtra(Constant.INTENT_TEST_DATE);
        int mondai = getIntent().getIntExtra(Constant.INTENT_MONDAI, 0);
         filename = getIntent().getStringExtra(Constant.INTENT_FILENAME);

        String mondai_title = getIntent().getStringExtra(Constant.INTENT_TITLE_Q);
        tvMondai.setText(Html.fromHtml(mondai_title, Html.FROM_HTML_MODE_LEGACY));
        setTitle(test_date);
        Log.i(TAG, "initView pos:" + pos);
        presenter = new JlptListeningPresenter(activity, level, test_date, mondai);
        setPageView();

        presenter.loadData(this);
        audio = new AudioPlayerManager(activity);

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        audio.stop();
    }

    /////////

    @OnClick(R.id.imgSpeak)
    public void actionSpeak() {
        Log.i(TAG, "speak filename:" + filename);
        if(audio.isPlaying()){
            audio.pause();
            imgSpeak.setBackgroundResource(R.drawable.play);
        }else{
            audio.start();
            imgSpeak.setBackgroundResource(R.drawable.pause);
        }
//        audio.stop();
//        audio.play(FOLDER + items.get(pos).getSound());

    }

    @OnClick(R.id.imgPre)
    public void actionPre() {
        if (pos == 0)
            return;
        pos--;
        hideButton();
        viewpager.setCurrentItem(pos);
    }

    @OnClick(R.id.imgNext)
    public void actionNext() {
        if (pos >= items.size() - 1)
            return;
        pos++;
        hideButton();
        viewpager.setCurrentItem(pos);
    }

    @OnClick(btnView)
    public void actionView() {
        JlptListeningFragment fragment = (JlptListeningFragment) viewpager.getAdapter().instantiateItem(viewpager, pos);
        JlptListeningDialog dialog = new JlptListeningDialog(activity, fragment.item.explain);
        dialog.show();
    }

    //    =====
    @Override
    public void onCallback(List<JlptListeningEntity> data) {
        Log.i(TAG, "onCallback data");
        items = data;

        if (items.get(0).title != null && !items.get(0).title.equals(""))
            tvTitleQuestion.setText(items.get(0).title + "");

        JlptListeningEntity item = items.get(pos);

        hideButton();
        adapter = new JlptListeningAdapter(getSupportFragmentManager(), items.size());
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(pos);
//        actionSpeak();
        audio.play2(filename);
    }


    @Override
    public void onFail(String err) {

    }

    private void setPageView() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pos = position;
                hideButton();
//                JlptListeningEntity item = items.get(position);
//                tvNum.setText(item.num + "");
//                actionSpeak();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void hideButton() {
        if (items.size() <= 1) {
            imgPre.setVisibility(View.GONE);
            imgNext.setVisibility(View.GONE);
        } else {
            if (pos == 0) {
                imgPre.setVisibility(View.GONE);
                imgNext.setVisibility(View.VISIBLE);
            } else if (pos >= items.size() - 1) {
                imgPre.setVisibility(View.VISIBLE);
                imgNext.setVisibility(View.GONE);
            } else {
                imgPre.setVisibility(View.VISIBLE);
                imgNext.setVisibility(View.VISIBLE);
            }
        }
    }

}
