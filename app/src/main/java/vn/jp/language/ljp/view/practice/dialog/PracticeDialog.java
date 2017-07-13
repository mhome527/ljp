package vn.jp.language.ljp.view.practice.dialog;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PracticeEntity;
import vn.jp.language.ljp.view.BaseDialog;
import vn.jp.language.ljp.view.practice.list.IPracticeInterface;

/**
 * Created by Administrator on 7/12/2017.
 */

public class PracticeDialog extends BaseDialog {

    @BindView(R.id.imgBookmark)
    ImageButton imgBookmark;

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

//    PracticeDialogPresenter presenter;

    int ansType = 0; //0: don't choice; 1: choice true; 2: choice wrong
    PracticeEntity item;

    IPracticeInterface iPracticeInterface;
    int pos;

    public PracticeDialog(Context context, int pos, PracticeEntity item, IPracticeInterface iPracticeInterface) {
        super(context);
        this.item = item;
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
        if (item.getBookmarks() == 0)
            imgBookmark.setImageResource(R.drawable.heart_off);
        else
            imgBookmark.setImageResource(R.drawable.heart_on);

        tvQuestion.setText(item.getQuestion());
        tvQ1.setText(item.getQ1());
        tvQ2.setText(item.getQ2());
        tvQ3.setText(item.getQ3());
        tvQ4.setText(item.getQ4());
    }

    @OnClick(R.id.imgBookmark)
    public void actionBookmark() {

        if (item.getBookmarks() == 0) {
            imgBookmark.setImageResource(R.drawable.heart_on);
//            presenter.updateBookmark(1);
            iPracticeInterface.onBookmark(pos, 1);
        } else {
            imgBookmark.setImageResource(R.drawable.heart_off);
//            presenter.updateBookmark(0);
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

    private void setView(int ans, ImageView img) {
        if (ans == item.getAns()) {
            img.setImageResource(R.drawable.circle_true);
            if (ansType == 0)
                ansType = 1;
        } else {
            img.setImageResource(R.drawable.circle_wrong);
            ansType = 2;
        }

        iPracticeInterface.onAns(pos, ansType);
//        presenter.updateAnswer(ansType);
    }


}
