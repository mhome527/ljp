package vn.jp.language.ljp.view.practice.listening;

import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

/**
 * Created by Administrator on 7/18/2017.
 */

public class PracticeListeningActivity extends BaseActivity<PracticeListeningActivity> implements ICallback<List<PracticeEntity>> {
    private final String TAG = "PracticeListeningActivity";
    private final String FOLDER = "n/";

    @BindView(R.id.tvQuestion)
    TextView tvQuestion;

    @BindView(R.id.imgQ1)
    ImageView imgQ1;

    @BindView(R.id.tvQ1)
    TextView tvQ1;

    @BindView(R.id.imgQ2)
    ImageView imgQ2;

    @BindView(R.id.tvQ2)
    TextView tvQ2;

    @BindView(R.id.imgQ3)
    ImageView imgQ3;

    @BindView(R.id.tvQ3)
    TextView tvQ3;

    @BindView(R.id.imgQ4)
    ImageView imgQ4;

    @BindView(R.id.tvQ4)
    TextView tvQ4;

    @BindView(R.id.llQ4)
    LinearLayout llQ4;

    String titleQ;
    String filename;

    AudioPlayerManager audio;
    PracticeEntity item;
    int ansType = 0; //0: don't choice; 1: choice true; -1: choice wrong
    PracticeListeningPresenter presenter;


    @Override
    protected int getLayout() {
        return R.layout.practice_listening_layout;
    }

    @Override
    protected void initView() {
        int level = getIntent().getIntExtra(Constant.INTENT_LEVEL, 0);
        int idRef = getIntent().getIntExtra(Constant.INTENT_DETAIL_NUM, 0);
        titleQ = getIntent().getStringExtra(Constant.INTENT_TITLE_Q);
        filename = getIntent().getStringExtra(Constant.INTENT_FILE_NAME);
        presenter = new PracticeListeningPresenter(activity, level, idRef);
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

    @Override
    public void onCallback(List<PracticeEntity> data) {
        item = data.get(0);
        if (item.getBookmarks() != 0) {
            tvQ1.setText("1.");
            tvQ2.setText("2.");
            tvQ3.setText("3.");
            if (item.getQ4() != null && !item.getQ4().equals(""))
                tvQ4.setText("4.");

        } else {
            tvQ1.setText("1." + item.getQ1());
            tvQ2.setText("2." + item.getQ2());
            tvQ3.setText("3." + item.getQ3());
            if (item.getQ4() != null && !item.getQ4().equals(""))
                tvQ4.setText("4." + item.getQ4());

        }

        if (item.getQ4() != null && !item.getQ4().equals("")) {
            llQ4.setVisibility(View.VISIBLE);
        } else {
            llQ4.setVisibility(View.GONE);
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvQuestion.setText(Html.fromHtml(item.getQuestion(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvQuestion.setText(Html.fromHtml(item.getQuestion()));
        }

    }

    @Override
    public void onFail(String err) {

    }
    /////////

    @OnClick(R.id.imgSpeak)
    public void actionSpeak() {
        Log.i(TAG, "speak filename:" + filename);
//        audio.stop();
//        audio.play(FOLDER + filename);
    }

    @OnClick(R.id.imgQ1)
    public void actionQ1() {
        setView(1, imgQ1);
    }

    @OnClick(R.id.tvQ1)
    public void actionTvQ1() {
        setView(1, imgQ1);
    }

    @OnClick(R.id.imgQ2)
    public void actionQ2() {
        setView(2, imgQ2);
    }

    @OnClick(R.id.tvQ2)
    public void actionTvQ2() {
        setView(2, imgQ2);
    }


    @OnClick(R.id.imgQ3)
    public void actionQ3() {
        setView(3, imgQ3);
    }

    @OnClick(R.id.tvQ3)
    public void actionTvQ3() {
        setView(3, imgQ3);
    }


    @OnClick(R.id.imgQ4)
    public void actionQ4() {
        setView(4, imgQ4);
    }

    @OnClick(R.id.tvQ4)
    public void actionTvQ4() {
        setView(4, imgQ4);
    }


    @OnClick(R.id.btnView)
    public void actionView() {
        String ans = "";
        if (item.getBookmarks() == 1) {
            if (item.getQuestion() != null && !item.getQuestion().trim().equals(""))
                ans += "<br/><br/>" + item.getQuestion();
            else
                ans += "<br/>";
            ans += "<br/> 1." + item.getQ1() + "<br/>"
                    + " 2." + item.getQ2() + "<br/>"
                    + " 3." + item.getQ3();
            if (item.getQ4() != null && !item.getQ4().trim().equals(""))
                ans += "<br/> 4." + item.getQ4();

        }
        PracticeListeningDialog dialog = new PracticeListeningDialog(activity, titleQ + ans);
        dialog.show();
//        finish();
    }

    private void setView(int ans, ImageView img) {
        if (ans == item.getAns()) {
            img.setImageResource(R.drawable.circle_true);
            if (ansType == 0)
                ansType = 1;
        } else {
            img.setImageResource(R.drawable.circle_wrong);
            ansType = -1;
        }

        presenter.updateAns(item.getNum(), ansType);
    }

}
