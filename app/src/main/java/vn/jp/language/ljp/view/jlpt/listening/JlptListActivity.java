package vn.jp.language.ljp.view.jlpt.listening;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Environment;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.db.table.PracticeTable;
import vn.jp.language.ljp.entity.JlptEntity;
import vn.jp.language.ljp.entity.JlptMstEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Toaster;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IJlptClickListener;
import vn.jp.language.ljp.view.jlpt.listening_detail.JlptListeningActivity;
import vn.jp.language.ljp.view.practice.list.PracticeListAdapter;
import vn.jp.language.ljp.view.purchase.PurchaseActivity;

/**
 * Created by Administrator on 7/7/2017.
 */

public class JlptListActivity extends PurchaseActivity<JlptListActivity> implements IJlptClickListener {
    private final String TAG = "JlptListActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };

    List<JlptMstEntity> items;
    PracticeListAdapter adapter;
    JlptListPresenter presenter;
    int level;
    int kind;

    // declare the dialog as a member field of your activity
    ProgressDialog mProgressDialog;

    @Override
    protected int getLayout() {
        return R.layout.jlpt_listening_list_layout;
    }

    @Override
    protected void initView() {
        Common.setupRecyclerView(activity, recyclerView, null);
        level = getIntent().getIntExtra(Constant.INTENT_LEVEL, PracticeTable.LEVEL_N5);
        kind = getIntent().getIntExtra(Constant.INTENT_KIND, 1);
        presenter = new JlptListPresenter(activity, level, kind);
        setTitle("聴解ーN" + level);

        Common.verifyStoragePermissions(activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_practice_list, menu);
//        return true;
//    }

    // ================= Purchase ====================
    @Override
    protected void dealWithIabSetupSuccess() {
        if (getItemPurchased() == Constant.ITEM_PURCHASED) {
            Log.i(TAG, "WithIabSetupSuccess...item purchased");
            isPurchased = true;
            adapter.setPurchased(isPurchased);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

            /// Test only
//            if (BuildConfig.DEBUG)
//                clearPurchaseTest();

        } else {
            Log.i(TAG, "WithIabSetupSuccess item not purchase");
            isPurchased = false;
        }
    }

    @Override
    protected void dealWithIabSetupFailure() {

    }
    //    ========================== END PURCHASE ==============

    //   ==============  IJlptClickListener - item click
    @Override
    public void onClick(int position, int mondai) {
        Log.i(TAG, "item click:" + position + "; mondai:" + mondai);

        presenter.loadMondai(items.get(position).test_date, mondai, new ICallback() {
            @Override
            public void onCallback(Object data) {
                JlptEntity item = (JlptEntity) data;
                if (!Common.isPermission(activity)) {
                    new Toaster(activity).popToast("Permission denied!!!");
                    Common.verifyStoragePermissions(activity);
                } else {
                    if(item.filename == null || item.filename.equals(""))
                        return;
                    String path_file = Environment.getExternalStorageDirectory().toString() + Constant.FOLDER_JLPT + "/" + item.filename;
                    if (Common.isExistFile(path_file)) {
                        startJlptListening(item);
                    } else {
                        Log.i(TAG, "file not exist, ->download");
                        // instantiate it within the onCreate method
                        mProgressDialog = new ProgressDialog(activity);
//                mProgressDialog.setMessage("A message");
                        mProgressDialog.setIndeterminate(true);
                        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        mProgressDialog.setCancelable(false);
                        presenter.downloadFile(item);
                    }
                }
            }

            @Override
            public void onFail(String err) {

            }
        });


    }
//   ============= END IJlptClickListener

    public void startJlptListening(JlptEntity item) {
        Intent i = new Intent(activity, JlptListeningActivity.class);
        i.putExtra(Constant.INTENT_LEVEL, item.level);
        i.putExtra(Constant.INTENT_TEST_DATE, item.test_date);
        i.putExtra(Constant.INTENT_MONDAI, item.mondai);
        i.putExtra(Constant.INTENT_TITLE_Q, item.title);
        i.putExtra(Constant.INTENT_FILENAME, item.filename);
        startActivity(i);
    }

    private void loadData() {
        presenter.loadData(new ICallback() {
            @Override
            public void onCallback(Object data) {
                items = (List<JlptMstEntity>) data;
                JlptListAdapter adapter = new JlptListAdapter(items, activity);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }


//    private void setTitleQ(int value) {
//        setTitleQ(value, items.size());
//    }
//
//    private void setTitleQ(int v1, int v2) {
//        setTitle(presenter.getTitle(v1, v2));
//    }

}
