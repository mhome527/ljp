package vn.jp.language.ljp.view.practice.dashboard;

import static vn.jp.language.ljp.Constant.KIND_LISTENING;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.view.BaseFragment;
import vn.jp.language.ljp.view.jlpt.listening.JlptListActivity;
import vn.jp.language.ljp.view.jlpt.grammar.JlptGrammarListActivity;
import vn.jp.language.ljp.view.practice.list.PracticeListActivity;

/**
 * Created by Administrator on 7/21/2017.
 */

public class PracticeDashboardFragment extends BaseFragment<PracticeDashboardActivity> {

    @BindView(R.id.btnListening)
    TextView btnListening;

    @BindView(R.id.btnGrammar)
    TextView btnGrammar;

    @BindView(R.id.btnReading)
    TextView btnReading;

    @BindView(R.id.btnVocabulary)
    TextView btnVocabulary;

    @BindView(R.id.btnKanji)
    TextView btnKanji;

    //    public int level = 5;
    PracticePresenter presenter;

    int listeningCorrect;
    int listeningAll;

    int grammarCorrect;
    int grammarAll;

    int readingCorrect;
    int readingAll;

    int vocabularyCorrect;
    int vocabularyAll;

    int kanjiCorrect;
    int kanjiAll;


    @Override
    public int getLayout() {
//        return R.layout.practice_dashboard_page;
        return R.layout.practice_dashboard_page_new;
    }

    @Override
    public void initView(View root) {

        presenter = new PracticePresenter(activity, activity.level, -1);

        listeningCorrect = presenter.countCorrect(PracticeTable.TYPE_LISTENING);
        listeningAll = presenter.countAll(PracticeTable.TYPE_LISTENING);

        grammarCorrect = presenter.countCorrect(PracticeTable.TYPE_GRAMMAR);
        grammarAll = presenter.countAll(PracticeTable.TYPE_GRAMMAR);

        readingCorrect = presenter.countCorrect(PracticeTable.TYPE_READING);
        readingAll = presenter.countAll(PracticeTable.TYPE_READING);

        vocabularyCorrect = presenter.countCorrect(PracticeTable.TYPE_VOCABULARY);
        vocabularyAll = presenter.countAll(PracticeTable.TYPE_VOCABULARY);

        kanjiCorrect = presenter.countCorrect(PracticeTable.TYPE_KANJI);
        kanjiAll = presenter.countAll(PracticeTable.TYPE_KANJI);

        btnListening.setText(Html.fromHtml(getString(R.string.title_dashboard_exercises, listeningCorrect, listeningAll), Html.FROM_HTML_MODE_LEGACY));
        btnGrammar.setText(Html.fromHtml(getString(R.string.title_dashboard_exercises, grammarCorrect, grammarAll), Html.FROM_HTML_MODE_LEGACY));
        btnReading.setText(Html.fromHtml(getString(R.string.title_dashboard_exercises, readingCorrect, readingAll), Html.FROM_HTML_MODE_LEGACY));
        btnVocabulary.setText(Html.fromHtml(getString(R.string.title_dashboard_exercises_1, vocabularyCorrect, vocabularyAll), Html.FROM_HTML_MODE_LEGACY));
        btnKanji.setText(Html.fromHtml(getString(R.string.title_dashboard_exercises_2, kanjiCorrect, kanjiAll), Html.FROM_HTML_MODE_LEGACY));

    }

    @OnClick(R.id.btnListening)
    public void actionListening() {
        startIntent(PracticeTable.TYPE_LISTENING, listeningCorrect, listeningAll);
    }

    @OnClick(R.id.btnListeningJ)
    public void actionListeningJ() {
        Intent i = new Intent(activity, JlptListActivity.class);
        i.putExtra(Constant.INTENT_LEVEL, activity.level);
        i.putExtra(Constant.INTENT_KIND, KIND_LISTENING);
        startActivity(i);
    }

    @OnClick(R.id.btnGrammar)
    public void actionGrammar() {
        startIntent(PracticeTable.TYPE_GRAMMAR, grammarCorrect, grammarAll);
    }

    @OnClick(R.id.btnGrammarJ)
    public void actionGrammarJ() {
        Intent i = new Intent(activity, JlptGrammarListActivity.class);
        i.putExtra(Constant.INTENT_LEVEL, activity.level);
        i.putExtra(Constant.INTENT_KIND, Constant.KIND_GRAMMAR);
        startActivity(i);
    }

    @OnClick(R.id.btnReading)
    public void actionReading() {
        startIntent(PracticeTable.TYPE_READING, readingCorrect, readingAll);
    }

    @OnClick(R.id.btnReadingJ)
    public void actionReadingJ() {
        Intent i = new Intent(activity, JlptGrammarListActivity.class);
        i.putExtra(Constant.INTENT_LEVEL, activity.level);
        i.putExtra(Constant.INTENT_KIND, Constant.KIND_READING);
        startActivity(i);
    }

    @OnClick(R.id.btnVocabulary)
    public void actionVocabulary() {
        startIntent(PracticeTable.TYPE_VOCABULARY, vocabularyCorrect, vocabularyAll);
    }

    @OnClick(R.id.btnKanji)
    public void actionKanji() {
        startIntent(PracticeTable.TYPE_KANJI, kanjiCorrect, kanjiAll);
    }

    @OnClick(R.id.btnVocabularyJ)
    public void actionVocabularyJ() {
        Intent i = new Intent(activity, JlptGrammarListActivity.class);
        i.putExtra(Constant.INTENT_LEVEL, activity.level);
        i.putExtra(Constant.INTENT_KIND, Constant.KIND_VOCABULARY);
        startActivity(i);
    }


    private void startIntent(int kind, int v1, int v2) {
        Intent i = new Intent(activity, PracticeListActivity.class);
        i.putExtra(Constant.INTENT_KIND, kind);
        i.putExtra(Constant.INTENT_LEVEL, activity.level);
        i.putExtra(Constant.INTENT_V1, v1);
        i.putExtra(Constant.INTENT_V2, v2);
        startActivity(i);
    }

}
