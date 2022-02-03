package vn.jp.language.ljp.view.dashboard;

import android.content.Intent;
import android.view.View;

import androidx.multidex.BuildConfig;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.DashboardEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Utility;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.alphabet.AlphabetActivity;
import vn.jp.language.ljp.view.date.DateActivity;
import vn.jp.language.ljp.view.grammar.GrammarActivity;
import vn.jp.language.ljp.view.kanji.KanjiActivity;
import vn.jp.language.ljp.view.number.NumberActivity;
import vn.jp.language.ljp.view.phrases.PhraseActivity;
import vn.jp.language.ljp.view.practice.dashboard.PracticeDashboardActivity;
import vn.jp.language.ljp.view.test.AndroidDatabaseManager;
import vn.jp.language.ljp.view.words.WordActivity;

public class DashboardActivity extends BaseActivity<DashboardActivity> {

    private String TAG = "DashboardActivity";

    List<DashboardEntity> listData;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    DashboardAdapter adapter;
    View llBg;

    @Override
    protected int getLayout() {
        return R.layout.dashboard_layout;
    }

    @Override
    protected void initView() {
        int column;
        Log.i(TAG, "initView text");
        setTitle(getString(R.string.title_dashboard));
        createData();

        if (Common.isTablet(activity))
            column = 3;
        else
            column = 2;

        Common.setupRecyclerViewGrid(activity, recyclerView, column, new IClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity2(AlphabetActivity.class);
                        break;
                    case 1:
                        startActivity2(WordActivity.class);
                        break;
                    case 2:
                        startActivity2(GrammarActivity.class);
                        break;
                    case 3:
                        startActivity2(PracticeDashboardActivity.class);
                        break;
                    case 4:
                        startActivity2(PhraseActivity.class);
                        break;
                    case 5:
                        startActivity2(NumberActivity.class);
                        break;
                    case 6:
                        startActivity2(KanjiActivity.class);
                        break;
                    case 7:
                        startActivity2(DateActivity.class);
                        break;
                    default:
                        break;
                }
//
                llBg = view.findViewById(R.id.llBg);
                llBg.setSelected(true);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });
        adapter = new DashboardAdapter(listData);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume---------------------");
        if (llBg != null)
            llBg.setSelected(false);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.llOtherApp)
    public void actionOtherApp() {
        if (BuildConfig.DEBUG) {
            Intent i = new Intent(activity, AndroidDatabaseManager.class);
            startActivity(i);
        } else
            Utility.installVnApp(activity);
    }

    private void createData() {
        listData = new ArrayList<>();
        listData.add(new DashboardEntity(R.drawable.ic_alphabet, getString(R.string.title_alphabet)));
        listData.add(new DashboardEntity(R.drawable.ic_animal, getString(R.string.title_word)));
        listData.add(new DashboardEntity(R.drawable.ic_grammar, getString(R.string.title_grammar)));
        listData.add(new DashboardEntity(R.drawable.ic_jplt, getString(R.string.title_test)));
        listData.add(new DashboardEntity(R.drawable.ic_phrase, getString(R.string.title_phrase)));
        listData.add(new DashboardEntity(R.drawable.ic_number, getString(R.string.title_counter)));
        listData.add(new DashboardEntity(R.drawable.ic_kanji, getString(R.string.title_kanji)));
        listData.add(new DashboardEntity(R.drawable.ic_dates, getString(R.string.title_date)));
//        listData.add(new DashboardEntity(R.drawable.button_word_on, getString(R.string.title_coming_soon)));
    }

    public void runActivity(Class<?> cls, View view) {

        startActivity2(cls);
    }

}
