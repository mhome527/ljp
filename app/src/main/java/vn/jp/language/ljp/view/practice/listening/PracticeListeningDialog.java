package vn.jp.language.ljp.view.practice.listening;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseDialog;


/**
 * Created by Administrator on 7/12/2017.
 */

public class PracticeListeningDialog extends BaseDialog {

    @BindView(R.id.tvContent)
    TextView tvContent;

    String content;

    public PracticeListeningDialog(Context context, String content) {
        super(context);
        this.content = content;
    }

    @Override
    public int getLayout() {
        return R.layout.practice_listening_dialog;
    }

    @Override
    public void initView(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvContent.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvContent.setText(Html.fromHtml(content));
        }
    }

    @OnClick(R.id.btnClose)
    public void actionClose() {
        dismiss();
    }

}
