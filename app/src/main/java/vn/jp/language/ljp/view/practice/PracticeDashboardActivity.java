package vn.jp.language.ljp.view.practice;

import android.content.Intent;
import android.view.MenuItem;

import butterknife.OnClick;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_practice_dashboard, menu);
//
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
//            case R.id.menuBookmark:
//                onBackPressed();
//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.tvN1Grammar)
    public void actionN1Grammar() {
        startIntent(PracticeTable.LEVEL_N1, PracticeTable.TYPE_GRAMMAR);
    }

    @OnClick(R.id.tvN1Reading)
    public void actionN1Reading() {
        startIntent(PracticeTable.LEVEL_N1, PracticeTable.TYPE_READING);
    }

    @OnClick(R.id.tvN1Vocabulary)
    public void actionN1Vocabulary() {
        startIntent(PracticeTable.LEVEL_N1, PracticeTable.TYPE_VOCABULARY);
    }

    @OnClick(R.id.tvN1Kanji)
    public void actionN1Kanji() {
        startIntent(PracticeTable.LEVEL_N1, PracticeTable.TYPE_KANJI);
    }

    private void startIntent(int level, int kind) {
        Intent i = new Intent(activity, PracticeListActivity.class);
        i.putExtra(Constant.INTENT_KIND, kind);
        i.putExtra(Constant.INTENT_LEVEL, level);
        startActivity(i);
    }

}
