package vn.jp.language.ljp.view.jlpt.listening;

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

public class JlptListeningDialog extends BaseDialog {

    @BindView(R.id.tvContent)
    TextView tvContent;

    String content;

    public JlptListeningDialog(Context context, String content) {
        super(context);
        this.content = content;
    }

    @Override
    public int getLayout() {
        return R.layout.practice_listening_dialog;
    }

    @Override
    public void initView(View view) {
        tvContent.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
    }

    @OnClick(R.id.btnClose)
    public void actionClose() {
        dismiss();
    }

}
