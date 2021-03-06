package vn.jp.language.ljp.view.practice.dialog;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseDialog;
import vn.jp.language.ljp.view.practice.list.IPracticeInterface;
import vn.jp.language.ljp.view.practice.reading.PracticeHintDialog;
import vn.jp.language.ljp.view.purchase.PurchaseActivity;

/**
 * Created by Administrator on 7/12/2017.
 */

public class PracticeDialog extends BaseDialog {

    private final String TAG = "PracticeDialog";


    @BindView(R.id.imgBookmark)
    ImageButton imgBookmark;

    @BindView(R.id.tvNum)
    TextView tvNum;

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

    @BindView(R.id.imgPre)
    ImageButton imgPre;

    @BindView(R.id.imgNext)
    ImageButton imgNext;

    ///////////
    @BindView(R.id.btnHint)
    Button btnHint;

    @BindView(R.id.llHint)
    LinearLayout llHint;

    @BindView(R.id.tvHint)
    TextView tvHint;

    /////////////
//    PracticeDialogPresenter presenter;

    int ansType = 0; //0: don't choice; 1: choice true; -1: choice wrong
    List<PracticeEntity> items;

    IPracticeInterface iPracticeInterface;
    //    IActionDialog iActionDialog;
    int pos;
    //    int length;
    PurchaseActivity activity;

    public PracticeDialog(PurchaseActivity activity, int pos, List<PracticeEntity> items, IPracticeInterface iPracticeInterface) {
        super(activity);
        this.activity = activity;

        this.items = items;
        this.pos = pos;
        this.iPracticeInterface = iPracticeInterface;


//        presenter = new PracticeDialogPresenter(context, level, item.getKind(), item.getNum());
    }

    @Override
    public int getLayout() {
        return R.layout.practice_dialog_layout;
    }

    @Override
    public void initView(View view) {
        tvQuestion.setMinLines(3);
        setData(items.get(pos));

        llHint.setVisibility(View.GONE);
        hideButton();
    }

    @OnClick(R.id.imgBookmark)
    public void actionBookmark() {

        if (items.get(pos).getBookmarks() == 0) {
            imgBookmark.setImageResource(R.drawable.heart_on);
            iPracticeInterface.onBookmark(pos, 1);
        } else {
            imgBookmark.setImageResource(R.drawable.heart_off);
            iPracticeInterface.onBookmark(pos, 0);
        }
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


    @OnClick(R.id.tvClose)
    public void actionClose() {
        this.dismiss();
    }

    @OnClick(R.id.imgPre)
    public void actionPre() {
        Log.i(TAG, "actionPre ===============>");
        if (pos == 0)
            return;
        pos--;
        resetView();
        hideButton();
        setData(items.get(pos));
    }

    @OnClick(R.id.imgNext)
    public void actionNext() {
        Log.i(TAG, "actionNext  =====>");


        if (pos >= items.size() - 1)
            return;

        if (activity.isPurchased || items.get(pos + 1).getKind() == PracticeTable.TYPE_KANJI
                || items.get(pos + 1).getNum() <= Constant.TRIAL_GRAMMAR) {
            pos++;
            resetView();
            hideButton();
            setData(items.get(pos));
        } else {
            activity.purchaseItem();
        }

    }

    /// hint
    @OnClick(R.id.btnHint)
    public void actionHint() {
//        llHint.setVisibility(View.VISIBLE);
        String hint = items.get(pos).getHint();
        PracticeHintDialog dialog = new PracticeHintDialog(activity, hint);
        dialog.show();

    }

    @OnClick(R.id.btnCloseHint)
    public void actionCloseHint() {
//        llHint.setVisibility(View.GONE);
    }
    ////////////////////

    public void setData(PracticeEntity item) {
        String hint;
        if (item.getBookmarks() == 0)
            imgBookmark.setImageResource(R.drawable.heart_off);
        else
            imgBookmark.setImageResource(R.drawable.heart_on);

        hint = item.getHint();
        if (activity.lang.equals(Constant.VN) && activity.level == PracticeTable.LEVEL_N3
                && hint != null && !hint.equals("")) {
            btnHint.setVisibility(View.VISIBLE);
        } else
            btnHint.setVisibility(View.GONE);

//        tvQuestion.setText(item.getQuestion());

        tvNum.setText(item.getNum() + "");
        tvQ1.setText(item.getQ1());
        tvQ2.setText(item.getQ2());
        tvQ3.setText(item.getQ3());
        tvQ4.setText(item.getQ4());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvQuestion.setText(Html.fromHtml(item.getQuestion(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvQuestion.setText(Html.fromHtml(item.getQuestion()));
        }

//        if (item.getHint() != null && !item.getHint().equals(""))
//            tvHint.setText(item.getHint());
    }

    private void setView(int ans, ImageView img) {
        if (ans == items.get(pos).getAns()) {
            img.setImageResource(R.drawable.circle_true);
            if (ansType == 0)
                ansType = 1;
        } else {
            img.setImageResource(R.drawable.circle_wrong);
            ansType = -1;
        }

        iPracticeInterface.onAns(pos, ansType);
//        presenter.updateAnswer(ansType);
    }

    private void resetView() {
        ansType = 0;

        imgQ1.setImageResource(R.drawable.circle);
        imgQ2.setImageResource(R.drawable.circle);
        imgQ3.setImageResource(R.drawable.circle);
        imgQ4.setImageResource(R.drawable.circle);
    }


    private void hideButton() {
        if (items.size() == 1) {
            imgPre.setVisibility(View.INVISIBLE);
            imgNext.setVisibility(View.INVISIBLE);
        } else if (pos == 0) {
            imgPre.setVisibility(View.INVISIBLE);
            imgNext.setVisibility(View.VISIBLE);
        } else if (pos == items.size() - 1) {
            imgPre.setVisibility(View.VISIBLE);
            imgNext.setVisibility(View.INVISIBLE);
        } else {
            imgPre.setVisibility(View.VISIBLE);
            imgNext.setVisibility(View.VISIBLE);
        }
    }

}
