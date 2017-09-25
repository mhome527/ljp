package vn.jp.language.ljp.view.ono;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseDialog;
import vn.jp.language.ljp.view.custom.furigana.FuriganaView;

/**
 * Created by Administrator on 9/25/2017.
 */

public class OnoDialog extends BaseDialog {


    @BindView(R.id.tvJp)
    TextView tvJp;

    @BindView(R.id.viewEx)
    FuriganaView viewEx;

    String jp, text;

    public OnoDialog(@NonNull Context context, String jp, String text) {
        super(context);
        this.jp = jp;
        this.text = text;
    }

    @Override
    public int getLayout() {
        return R.layout.ono_dialog_layout;
    }

    @Override
    public void initView(View view) {
        tvJp.setText(jp);

        if (text != null && !text.equals("")) {

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
        }
    }
}
