package vn.jp.language.ljp.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vn.jp.language.ljp.Constant;

/**
 * Created by Administrator on 10/11/2016.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "BaseActivity";
    public ProgressDialog progressDialog;

    protected abstract int getLayout();

    protected abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
        setContentView(getLayout());
        initView();


        ////////////// END Import DB

    }

    @Override
    public void onClick(View v) {

    }

    protected <V extends View> V getView(int id) {
        return (V) this.findViewById(id);
    }

    protected void setListener(int id) {
        if (id > 0)
            findViewById(id).setOnClickListener(this);
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
