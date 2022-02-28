package vn.jp.language.ljp.view.practice.listening;

import static vn.jp.language.ljp.R.id.btnView;

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
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.sound.AudioPlayerManager;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 7/18/2017.
 */

public class PracticeListeningActivity extends BaseActivity<PracticeListeningActivity> implements ICallback<List<PracticeEntity>> {
    private final String TAG = "PracticeListeningActivity";
    private final String FOLDER = "n/";

    @BindView(R.id.tvNum)
    TextView tvNum;

    @BindView(R.id.imgBookmark)
    ImageButton imgBookmark;

    @BindView(R.id.imgPre)
    ImageButton imgPre;

    @BindView(R.id.imgNext)
    ImageButton imgNext;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    PracticeListeningAdapter adapter;

    AudioPlayerManager audio;
    List<PracticeEntity> items;

    int pos;
    int bookmark;
    PracticeListeningPresenter presenter;
    //    int countAll;
    int refId;

    @Override
    protected int getLayout() {
        return R.layout.practice_listening_main_layout;
    }

    @Override
    protected void initView() {

        int level = getIntent().getIntExtra(Constant.INTENT_LEVEL, 0);
        refId = getIntent().getIntExtra(Constant.INTENT_NUM, 0);
        Log.i(TAG, "initView pos:" + pos);
        presenter = new PracticeListeningPresenter(activity, level);
        setPageView();

        presenter.load(this);
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

    @OnClick(R.id.imgBookmark)
    public void actionBookmark() {
        bookmark = bookmark == 0 ? 1 : 0;
        Log.i(TAG, "actionBook " + bookmark);
        items.get(pos).setBookmarks(bookmark);
        setImageBookmark();
        activity.presenter.updateBookmark(items.get(pos).getNum(), bookmark, items.get(pos).getRef());
    }

    @OnClick(R.id.imgSpeak)
    public void actionSpeak() {
        Log.i(TAG, "speak filename:" + items.get(pos).getSound());
        audio.stop();
        audio.play(FOLDER + items.get(pos).getSound());
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

//        PracticeListeningFragment fragment = (PracticeListeningFragment) viewpager.getAdapter().instantiateItem(viewpager, viewpager.getCurrentItem());
        PracticeListeningFragment fragment = (PracticeListeningFragment) viewpager.getAdapter().instantiateItem(viewpager, pos);

        if (null != fragment && fragment.isVisible()) {
            String ans = "";
            PracticeEntity item = fragment.item;

            if (item.getQuestion() != null && !item.getQuestion().equals(""))
                ans += "<br/><br/><b>" + item.getQuestion() + "</b><br/><br/>";
            else
                ans += "<br/><br/>";

            ans += " 1. " + item.getQ1() + "<br/>"
                    + " 2. " + item.getQ2() + "<br/>"
                    + " 3. " + item.getQ3();
            if (item.getQ4() != null && !item.getQ4().trim().equals(""))
                ans += "<br/> 4. " + item.getQ4();

            PracticeListeningDialog dialog = new PracticeListeningDialog(activity, item.getTitle() + ans);
            dialog.show();
        }
    }

    //    =====
    @Override
    public void onCallback(List<PracticeEntity> data) {
        Log.i(TAG, "onCallback data");
        items = data;

        for (int i = 0; i < items.size(); i++) {
            Log.i(TAG, "item " + items.get(i).getRef() + " ;" + refId);
            if (items.get(i).getRef() == refId) {
                pos = i;
                break;
            }
        }
        PracticeEntity item = items.get(pos);
        bookmark = item.getBookmarks();
        setImageBookmark();
        tvNum.setText(item.getNum() + "");
        hideButton();
        adapter = new PracticeListeningAdapter(getSupportFragmentManager(), items.size());
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(pos);
        actionSpeak();
        setTitleQ(presenter.countCorrect());
    }

    @Override
    public void onFail(String err) {

    }

    public void setTitleQ(int count) {
//        setTitle(getString(R.string.title_n_listening, count, items.size()));
        setTitle(getString(R.string.title_n_listening));
    }

    private void setImageBookmark() {
        if (bookmark == 0)
            imgBookmark.setImageResource(R.drawable.heart_off);
        else
            imgBookmark.setImageResource(R.drawable.heart_on);
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
                PracticeEntity item = items.get(position);
                bookmark = item.getBookmarks();
                setImageBookmark();
                tvNum.setText(item.getNum() + "");
                actionSpeak();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void hideButton() {
        if (pos == 0)
            imgPre.setVisibility(View.GONE);
        else if (pos >= items.size() - 1)
            imgNext.setVisibility(View.GONE);
        else {
            imgPre.setVisibility(View.VISIBLE);
            imgNext.setVisibility(View.VISIBLE);
        }
    }

}
