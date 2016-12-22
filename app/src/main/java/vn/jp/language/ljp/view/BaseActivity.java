package vn.jp.language.ljp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import vn.jp.language.ljp.Constant;

/**
 * Created by Administrator on 10/11/2016.
 */

public abstract class BaseActivity<T> extends AppCompatActivity {

    private static String TAG = "BaseActivity";

    protected abstract int getLayout();

    protected abstract void initView();

    protected T activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        activity = (T) this;
        initView();

    }


    public void startActivity2(Class<?> cls) {
        Intent i = new Intent(BaseActivity.this, cls);
        startActivity(i);
    }

    public void startActivity2(Class<?> cls, int param) {
        Intent i = new Intent(BaseActivity.this, cls);
        i.putExtra(Constant.INTENT_KIND, param);
        startActivity(i);
    }


}
