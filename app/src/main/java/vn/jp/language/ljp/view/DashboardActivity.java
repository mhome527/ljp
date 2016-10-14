package vn.jp.language.ljp.view;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import vn.jp.language.ljp.R;

public class DashboardActivity extends BaseActivity{
    @Override
    protected int getLayout() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void initView() {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnColor:
                break;
            case R.id.mnSearch:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){

        }
    }
}
