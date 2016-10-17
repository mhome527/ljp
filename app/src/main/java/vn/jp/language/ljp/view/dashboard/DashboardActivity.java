package vn.jp.language.ljp.view.dashboard;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.DashboardEntity;
import vn.jp.language.ljp.view.BaseActivity;

public class DashboardActivity extends BaseActivity {

    private String TAG = "DashboardActivity";

    List<DashboardEntity> listData;
    GridView gridView;

    @Override
    protected int getLayout() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void initView() {
        gridView = getView(R.id.gridView);
        createData();

        gridView.setNumColumns(2); //TODO:check tablet

        gridView.setAdapter(new DashboardAdapter(this, listData));
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

    private void createData(){
        listData = new ArrayList<>();
        listData.add(new DashboardEntity(R.drawable.button_alphabet_on, "alphabet"));
        listData.add(new DashboardEntity(R.drawable.button_number_on, "number"));
        listData.add(new DashboardEntity(R.drawable.button_date_on, "date"));
        listData.add(new DashboardEntity(R.drawable.button_word_on, "word"));
        listData.add(new DashboardEntity(R.drawable.button_grammer_on, "grammar"));
        listData.add(new DashboardEntity(R.drawable.button_alphabet_on, "alphabet"));
    }
}
