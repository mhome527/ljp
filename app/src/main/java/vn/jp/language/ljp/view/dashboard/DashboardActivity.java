package vn.jp.language.ljp.view.dashboard;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.DashboardEntity;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.alphabet.AlphabetActivity;
import vn.jp.language.ljp.view.grammar.GrammarActivity;
import vn.jp.language.ljp.view.kanji.KanjiActivity;
import vn.jp.language.ljp.view.number.NumberActivity;

public class DashboardActivity extends BaseActivity<DashboardActivity> {

    private String TAG = "DashboardActivity";

    List<DashboardEntity> listData;

    @BindView(R.id.gridView)
    GridView gridView;

    @Override
    protected int getLayout() {
        return R.layout.dashboard_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView");
        createData();

        gridView.setNumColumns(2); //TODO:check tablet

        gridView.setAdapter(new DashboardAdapter(this, listData));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        DashboardActivity.this.startActivity2(AlphabetActivity.class);
                        break;
                    case 1:
                        DashboardActivity.this.startActivity2(NumberActivity.class);
                        break;
                    case 2:
                        DashboardActivity.this.startActivity2(GrammarActivity.class);
                        break;
                    case 3:
                        DashboardActivity.this.startActivity2(KanjiActivity.class);
                        break;
                }
            }
        });
    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.mnColor:
//                break;
//            case R.id.mnSearch:
//
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    private void createData() {
        listData = new ArrayList<>();
        listData.add(new DashboardEntity(R.drawable.button_alphabet_on, "alphabet"));
        listData.add(new DashboardEntity(R.drawable.button_number_on, "number"));
        listData.add(new DashboardEntity(R.drawable.button_date_on, "date"));
        listData.add(new DashboardEntity(R.drawable.button_word_on, "word"));
        listData.add(new DashboardEntity(R.drawable.button_grammer_on, "grammar"));
        listData.add(new DashboardEntity(R.drawable.button_alphabet_on, "alphabet"));
    }
}
