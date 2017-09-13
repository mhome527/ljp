package vn.jp.language.ljp.view.practice.reading;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.BaseDialog;

/**
 * Created by Administrator on 7/12/2017.
 */

public class PracticeHintDialog extends BaseDialog {

    private final String TAG = "PracticeHintDialog";


    @BindView(R.id.tvHint)
    TextView tvHint;

    /////////////

    BaseActivity activity;
    String hint;

    public PracticeHintDialog(BaseActivity activity, String hint) {
        super(activity);
        this.activity = activity;
        this.hint = hint;
    }

    @Override
    public int getLayout() {
        return R.layout.hint_vn_layout;
    }

    @Override
    public void initView(View view) {
        tvHint.setMovementMethod(new ScrollingMovementMethod());
        tvHint.setText(hint);
    }


    @OnClick(R.id.btnCloseHint)
    public void actionCloseHint() {
        dismiss();
    }
    ////////////////////


}
