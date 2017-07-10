package vn.jp.language.ljp.view.practice;

import android.content.Intent;

import butterknife.OnClick;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.practice.list.PracticeListActivity;

/**
 * Created by Administrator on 7/7/2017.
 */

public class PracticeDashboardActivity extends BaseActivity<PracticeDashboardActivity> {
    @Override
    protected int getLayout() {
        return R.layout.practice_layout;
    }

    @Override
    protected void initView() {

    }
    /////

    @OnClick(R.id.tvN1Grammar)
    public void actionN1Grammar() {
        startIntent(1, 1);
    }

    @OnClick(R.id.tvN1Reading)
    public void actionN1Reading() {
        startIntent(1, 1);
    }

    @OnClick(R.id.tvN1Vocabulary)
    public void actionN1Vocabulary() {
        startIntent(1, 1);
    }

    @OnClick(R.id.tvN1Kanji)
    public void actionN1Kanji() {
        startIntent(1, 1);
    }

    private void startIntent(int level, int kind) {
        Intent i = new Intent(activity, PracticeListActivity.class);
        i.putExtra(Constant.INTENT_KIND, kind);
        i.putExtra(Constant.INTENT_LEVEL, level);
        startActivity(i);
    }

}
