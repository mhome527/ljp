package vn.jp.language.ljp.view.ono;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.OnoEntity;
import vn.jp.language.ljp.view.BaseDialog;
import vn.jp.language.ljp.view.custom.furigana.FuriganaView;

/**
 * Created by Administrator on 9/25/2017.
 */

public class OnoDialog extends BaseDialog {


    @BindView(R.id.tvJp)
    TextView tvJp;

    @BindView(R.id.tvHide)
    TextView tvHide;

    @BindView(R.id.tvMean)
    TextView tvMean;

    @BindView(R.id.tvEx2)
    TextView tvEx2;

    @BindView(R.id.imgBookmark)
    ImageButton imgBookmark;

    @BindView(R.id.viewEx)
    FuriganaView viewEx;

    OnoEntity item;
    OnoInterface onoInterface;


    public OnoDialog(@NonNull Context context, OnoEntity item, OnoInterface onoInterface) {
        super(context);
        this.item = item;
        this.onoInterface = onoInterface;
    }

    @Override
    public int getLayout() {
        return R.layout.ono_dialog_layout;
    }

    @Override
    public void initView(View view) {
        tvJp.setText(item.getJp());
        tvMean.setText(item.getOt());
        String text = item.getEx();
        if (text != null && !text.equals("")) {
            tvHide.setText(text);
//        mPaintDetail = new TextPaint();
//        mPaintDetail.setColor(detailTextColor);
//        mPaintDetail.setShadowLayer(2.0f, 0f, 2.0f, Color.DKGRAY);
//        mPaintDetail.setTextSize(16 * metricScale);
//        mPaintDetail.setAntiAlias(true);
            TextPaint tp = new TextPaint();
            tp.setTextSize(45);

//        String text = "{彼女;かのじょ}sdafdsa fdsa fdsaf dsafdsa は{寒気;さむけ}を{防;ふせ}ぐために{厚;あつ}いコートを{着;き}ていた。";
            int mark_s = 0; // highlight 厚い in text (characters 11-13)
            int mark_e = 0;

            viewEx.text_set(tp, text, mark_s, mark_e);

            String ex2 = item.getEx2();
            if (ex2 != null && !ex2.equals(""))
                tvEx2.setText(ex2);
        }

        if (item.getBookmarks() == 0)
            imgBookmark.setImageResource(R.drawable.heart_off);
        else
            imgBookmark.setImageResource(R.drawable.heart_on);


    }

    @OnClick(R.id.imgBookmark)
    public void actionBookmark() {

        if (item.getBookmarks() == 0) {
            item.setBookmarks(1);
            imgBookmark.setImageResource(R.drawable.heart_on);
            onoInterface.onBookmark(item);
        } else {
            item.setBookmarks(0);
            imgBookmark.setImageResource(R.drawable.heart_off);
            onoInterface.onBookmark(item);
        }
    }

    @OnClick(R.id.btnClose)
    public void actionClose() {
        this.dismiss();
    }

}
