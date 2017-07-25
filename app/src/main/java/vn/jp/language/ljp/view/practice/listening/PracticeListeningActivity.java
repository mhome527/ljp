package vn.jp.language.ljp.view.practice.listening;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
import vn.jp.language.ljp.sound.AudioPlayerManager;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;

import static vn.jp.language.ljp.R.id.btnView;

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

    String titleQ;
    String filename;
    PracticeListeningAdapter adapter;

    AudioPlayerManager audio;
    List<PracticeEntity> items;

    int pos;
    int idRef;
    int bookmark;
    int ansType = 0; //0: don't choice; 1: choice true; -1: choice wrong
    PracticeListeningPresenter presenter;
    //    int countAll;
    int num;

    @Override
    protected int getLayout() {
        return R.layout.practice_listening_main_layout;
    }

    @Override
    protected void initView() {
//        setTitle(getString(R.string.title_n_listening));

        int level = getIntent().getIntExtra(Constant.INTENT_LEVEL, 0);
        num = getIntent().getIntExtra(Constant.INTENT_NUM, 0);
        Log.i(TAG, "initView pos:" + pos);

//        idRef = getIntent().getIntExtra(Constant.INTENT_DETAIL_NUM, 0);
//        bookmark = getIntent().getIntExtra(Constant.INTENT_BOOKMARK, 0);
//        titleQ = getIntent().getStringExtra(Constant.INTENT_TITLE_Q);
//        filename = getIntent().getStringExtra(Constant.INTENT_FILE_NAME);
        presenter = new PracticeListeningPresenter(activity, level);
        setPageView();

//        tvNum.setText(num + "");
        presenter.load(this);
        audio = new AudioPlayerManager(activity);
//        countAll = presenter.countAll();
//        setTitleQ(presenter.countCorrect());
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
        setBookmark();
        items.get(pos).setBookmarks(bookmark);
        activity.presenter.updateBookmark(items.get(pos).getNum(), bookmark, items.get(pos).getRef());
    }

    @OnClick(R.id.imgSpeak)
    public void actionSpeak() {
        Log.i(TAG, "speak filename:" + items.get(pos).getQ1());
//        audio.stop();
//        audio.play(FOLDER + filename);
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
//            Log.i(TAG, "actionView question: " + fragment.item.getQuestion());
            String ans = "";
            PracticeEntity item = fragment.item;
//            if (item.getBookmarks() == 1) {
//                if (item.getQuestion() != null && !item.getQuestion().trim().equals(""))
//                    ans += "<br/><br/>" + item.getQuestion();
//                else
//                    ans += "<br/>";
            if (item.getQuestion() != null && !item.getQuestion().equals(""))
                ans += "<br/><br/><b>" + item.getQuestion() + "</b><br/><br/>";
            else
                ans += "<br/><br/>";

            ans += " 1. " + item.getQ1() + "<br/>"
                    + " 2. " + item.getQ2() + "<br/>"
                    + " 3. " + item.getQ3();
            if (item.getQ4() != null && !item.getQ4().trim().equals(""))
                ans += "<br/> 4." + item.getQ4();

//            }
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
            Log.i(TAG, "item " + items.get(i).getRef() + " ;" + num);
            if (items.get(i).getRef() == num) {
                pos = i;
                break;
            }
        }

        tvNum.setText(items.get(pos).getNum() + "");
        hideButton();
        adapter = new PracticeListeningAdapter(getSupportFragmentManager(), items.size());
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(pos);
        setTitleQ(presenter.countCorrect());
    }

    @Override
    public void onFail(String err) {

    }

    public void setTitleQ(int count) {
        setTitle(getString(R.string.title_n_listening, count, items.size()));
    }

    private void setBookmark() {
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
                setBookmark();
                tvNum.setText(item.getNum() + "");
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
