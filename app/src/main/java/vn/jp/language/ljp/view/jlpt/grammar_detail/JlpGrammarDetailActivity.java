package vn.jp.language.ljp.view.jlpt.grammar_detail;

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
import vn.jp.language.ljp.entity.JlptGrammarEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by Administrator on 7/18/2017.
 */

public class JlpGrammarDetailActivity extends BaseActivity<JlpGrammarDetailActivity> implements ICallback<List<JlptGrammarEntity>> {
    private final String TAG = "PracticeListeningActivity";
    private final String FOLDER = "n/";

    @BindView(R.id.tvMondai)
    TextView tvMondai;

    @BindView(R.id.imgPre)
    ImageButton imgPre;

    @BindView(R.id.imgNext)
    ImageButton imgNext;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    JlptGrammarDetailAdapter adapter;

    List<JlptGrammarEntity> items;

    int pos = 0;
    JlptGrammarDetailPresenter presenter;

    public String test_date;
    public int kind;
    public int level;

    @Override
    protected int getLayout() {
        return R.layout.jlpt_grammar_detail_main_layout;
    }

    @Override
    protected void initView() {

        level = getIntent().getIntExtra(Constant.INTENT_LEVEL, 0);
        test_date = getIntent().getStringExtra(Constant.INTENT_TEST_DATE);
        kind = getIntent().getIntExtra(Constant.INTENT_KIND, 0);
        presenter = new JlptGrammarDetailPresenter(activity, level, test_date, kind);

        presenter.loadList(test_date, this);
        if (kind == Constant.KIND_VOCABULARY)
            setTitle("文字 N" + level + " (" + test_date + ")");
        else if (kind == Constant.KIND_GRAMMAR)
            setTitle("文法 N" + level + " (" + test_date + ")");
        else if (kind == Constant.KIND_READING)
            setTitle("読解 N" + level + " (" + test_date + ")");
        else if (kind == Constant.KIND_LISTENING)
            setTitle("聴解 N" + level + " (" + test_date + ")");
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
    }

    /////////


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

    //    =====
    @Override
    public void onCallback(List<JlptGrammarEntity> data) {
        Log.i(TAG, "onCallback data");
        items = data;

//        if (items.get(0).title != null && !items.get(0).title.equals(""))
//            tvTitleQuestion.setText(items.get(0).title + "");
//
//        JlptGrammarDetailEntity item = items.get(pos);

        hideButton();
        adapter = new JlptGrammarDetailAdapter(getSupportFragmentManager(), items.size());
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(pos);
        setPageView();
        tvMondai.setText(Constant.getMondai(activity.level, activity.kind, items.get(pos).mondai));

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
                tvMondai.setText(Constant.getMondai(activity.level, activity.kind, items.get(pos).mondai));

                hideButton();
//                JlptListeningEntity item = items.get(position);
//                tvNum.setText(item.num + "");
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

//    public void setMondai(String text){
//        tvMondai.setText(text);
//    }

}
