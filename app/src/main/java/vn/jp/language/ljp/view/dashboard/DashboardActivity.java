package vn.jp.language.ljp.view.dashboard;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.BuildConfig;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.DashboardEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Utility;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.RecyclerTouchListener;
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
import vn.jp.language.ljp.view.translate.TranslateActivity;
import vn.jp.language.ljp.view.words.WordActivity;

import static vn.jp.language.ljp.BaseApplication.mFirebaseAnalytics;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        Log.i(TAG, "initView text: ");
        setTitle(getString(R.string.title_dashboard));

        Utility.setLanguage(activity);
        listData = new ArrayList<>();

        createData();

        dialogLanguage = new Dialog(this);
        dialogLanguage.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogLanguage.setContentView(R.layout.dialog_language2_layout);

        initRecyclerView();
//        Common.setupRecyclerViewGrid(activity, recyclerView, column, new IClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        });
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


    public void showOtherApp() {
//        if (BuildConfig.DEBUG) {
//            Intent i = new Intent(activity, AndroidDatabaseManager.class);
//            startActivity(i);
//        } else {
        Utility.installVnApp(activity);

//            Bundle params = new Bundle();
//            params.putString("Lang", lang);
//            mFirebaseAnalytics.logEvent("vn_app", params);
//        }
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

    IClickListener iClickListener = new IClickListener() {
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
                    startActivity2(TranslateActivity.class);
                    screen = "TranslateActivity";
                    break;
                case 10:
                    startActivity2(OnoActivity.class);
                    screen = "OnoActivity";
                    break;
                case 11:
                    showPrivacyPolicy();
                    screen = "PrivacyPolicy";
                    break;

                case 12:
                    if (BuildConfig.DEBUG) {
                        startActivity2(AndroidDatabaseManager.class);
                        screen = "DEBUG DATABASE";
                    } else {
                        showOtherApp();
                        screen = "SHOW_APP_LVN";
                    }
                    break;
                default:
                    screen = "Nothing!!!!!!";
                    break;
            }
//
            llBg = view.findViewById(R.id.llBg);
            if (llBg != null)
                llBg.setSelected(true);

            //////////
            if (!BuildConfig.DEBUG) {
                // [START custom_event]
                Bundle params = new Bundle();
                params.putString("Lang", lang);
                mFirebaseAnalytics.logEvent(screen, params);
            }
        }

        @Override
        public void onLongClick(View view, int position) {

        }
    };


    private void initRecyclerView() {

        GridLayoutManager lLayout;

        if (Common.isTablet(activity))
            lLayout = new GridLayoutManager(activity, 3);
        else
            lLayout = new GridLayoutManager(activity, 2);

        lLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == adapter.getSize() - 2 || position == adapter.getSize() - 1) {
                    if (Common.isTablet(activity))
                        return 3;
                    else
                        return 2;
                } else {
                    return 1;
                }
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity, recyclerView, iClickListener));

    }

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
        listData.add(new DashboardEntity(R.drawable.ic_translate, getString(R.string.title_translate)));
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

    private void showPrivacyPolicy() {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        WebView wv = new WebView(this);
//        wv.loadUrl("https://sites.google.com/view/learnvietnamesevoice/home");
        wv.loadUrl("file:///android_asset/policy.html");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
