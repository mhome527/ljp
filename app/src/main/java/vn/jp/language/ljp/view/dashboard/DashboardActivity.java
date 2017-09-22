package vn.jp.language.ljp.view.dashboard;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.BuildConfig;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.DashboardEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Utility;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.alphabet.AlphabetActivity;
import vn.jp.language.ljp.view.dashboard.language.LanguageAdapter;
import vn.jp.language.ljp.view.dashboard.language.OnItemClickListener;
import vn.jp.language.ljp.view.date.DateActivity;
import vn.jp.language.ljp.view.food.FoodActivity;
import vn.jp.language.ljp.view.grammar.GrammarActivity;
import vn.jp.language.ljp.view.kanji.KanjiActivity;
import vn.jp.language.ljp.view.number.NumberActivity;
import vn.jp.language.ljp.view.ono.OnoActivity;
import vn.jp.language.ljp.view.phrases.PhraseActivity;
import vn.jp.language.ljp.view.practice.dashboard.PracticeDashboardActivity;
import vn.jp.language.ljp.view.test.AndroidDatabaseManager;
import vn.jp.language.ljp.view.words.WordActivity;

import static vn.jp.language.ljp.BaseApplication.mFirebaseAnalytics;

public class DashboardActivity extends BaseActivity<DashboardActivity> {

    private String TAG = "DashboardActivity";

    List<DashboardEntity> listData;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    MenuItem itemLanguage;
    DashboardAdapter adapter;
    View llBg;
    LanguageAdapter adapterLanguage;
    Dialog dialogLanguage;


    @Override
    protected int getLayout() {
        return R.layout.dashboard_layout;
    }

    @Override
    protected void initView() {
        int column;
        Log.i(TAG, "initView text: " + Constant.MY_TEXT);
        setTitle(getString(R.string.title_dashboard));

        Utility.setLanguage(activity);
        listData = new ArrayList<>();

        createData();

        dialogLanguage = new Dialog(this);
        dialogLanguage.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLanguage.setContentView(R.layout.dialog_language2_layout);

        if (Common.isTablet(activity))
            column = 3;
        else
            column = 2;

        Common.setupRecyclerViewGrid(activity, recyclerView, column, new IClickListener() {
            @Override
            public void onClick(View view, int position) {
                String screen;

                switch (position) {
                    case 0:
                        startActivity2(AlphabetActivity.class);
                        screen = "AlphabetActivity";
                        break;
                    case 1:
                        startActivity2(WordActivity.class);
                        screen = "WordActivity";
                        break;
                    case 2:
                        startActivity2(GrammarActivity.class);
                        screen = "GrammarActivity";
                        break;
                    case 3:
                        startActivity2(PracticeDashboardActivity.class);
                        screen = "PracticeDashboardActivity";
                        break;
                    case 4:
                        startActivity2(PhraseActivity.class);
                        screen = "PhraseActivity";
                        break;
                    case 5:
                        startActivity2(FoodActivity.class);
                        screen = "FoodActivity";
                        break;
                    case 6:
                        startActivity2(NumberActivity.class);
                        screen = "NumberActivity";
                        break;
                    case 7:
                        startActivity2(KanjiActivity.class);
                        screen = "KanjiActivity";
                        break;
                    case 8:
                        startActivity2(DateActivity.class);
                        screen = "DateActivity";
                        break;
                    case 9:
                        startActivity2(OnoActivity.class);
                        screen = "DateActivity";
                        break;
                    default:
                        screen = "Nothing!!!!!!";
                        break;
                }
//
                llBg = view.findViewById(R.id.llBg);
                llBg.setSelected(true);

                //////////
                if (!BuildConfig.DEBUG) {
                    // [START custom_event]
                    Bundle params = new Bundle();
                    params.putString("Lang", lang);
//                    params.putString("Language", );
                    mFirebaseAnalytics.logEvent(screen, params);
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        itemLanguage = menu.findItem(R.id.menuLang);

        setIconLanguage();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menuLang:
                showDialogLanguage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.llOtherApp)
    public void actionOtherApp() {
        if (BuildConfig.DEBUG) {
            Intent i = new Intent(activity, AndroidDatabaseManager.class);
            startActivity(i);
        } else
            Utility.installVnApp(activity);
    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(String lang) {
            activity.lang = lang;
            activity.pref.putStringValue(lang, Constant.TYPE_LANGUAGE);
//            adapterLanguage.setLang(lang);
            setIconLanguage();
            Utility.setLanguage(activity);
            ///

            ///
            setTitle(getString(R.string.title_dashboard));
            createData();

            adapter.notifyDataSetChanged();

            dialogLanguage.dismiss();
        }
    };

    private void setIconLanguage() {
        if (lang.equals(Constant.EN))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.english));
        else if (lang.equals(Constant.KO))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.korea));
        else if (lang.equals(Constant.FR))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.france));
        else if (lang.equals(Constant.ZH))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.china));
        else if (lang.equals(Constant.ES))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.spanish));
        else if (lang.equals(Constant.VN))
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.vietnam));
        else
            itemLanguage.setIcon(getResources().getDrawable(R.drawable.english));
    }

    private void createData() {
        listData.clear();
//        listData = new ArrayList<>();
        listData.add(new DashboardEntity(R.drawable.ic_alphabet, getString(R.string.title_alphabet)));
        listData.add(new DashboardEntity(R.drawable.ic_animal, getString(R.string.title_word)));
        listData.add(new DashboardEntity(R.drawable.ic_grammar, getString(R.string.title_grammar)));
        listData.add(new DashboardEntity(R.drawable.ic_jplt, getString(R.string.title_test)));
        listData.add(new DashboardEntity(R.drawable.ic_phrase, getString(R.string.title_phrase)));
        listData.add(new DashboardEntity(R.drawable.ic_food, getString(R.string.title_food)));
        listData.add(new DashboardEntity(R.drawable.ic_number, getString(R.string.title_counter)));
        listData.add(new DashboardEntity(R.drawable.ic_kanji, getString(R.string.title_kanji)));
        listData.add(new DashboardEntity(R.drawable.ic_dates, getString(R.string.title_date)));
        listData.add(new DashboardEntity(R.drawable.ic_ono, getString(R.string.title_ono)));
    }

    private void showDialogLanguage() {

        Button dialogButton = (Button) dialogLanguage.findViewById(R.id.btnChangeLang);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLanguage.dismiss();
            }
        });
        RecyclerView recyclerView = (RecyclerView) dialogLanguage.findViewById(R.id.recyclerView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        // Disabled nested scrolling since Parent scrollview will scroll the content.
        recyclerView.setNestedScrollingEnabled(false);

        adapterLanguage = new LanguageAdapter(activity, lang, onItemClickListener);
        recyclerView.setAdapter(adapterLanguage);

        dialogLanguage.show();
    }
}
