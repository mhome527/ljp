package vn.jp.language.ljp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 10/11/2016.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected abstract int getLayout();

    protected abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
        setContentView(getLayout());
        initView();
    }

    protected void setListener(int id) {
        if (id > 0)
            findViewById(id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
