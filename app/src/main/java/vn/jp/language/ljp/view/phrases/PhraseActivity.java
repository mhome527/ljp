package vn.jp.language.ljp.view.phrases;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;

import java.util.List;

import butterknife.BindView;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PhraseEntity;
import vn.jp.language.ljp.sound.AudioPlayerManager;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.ICallback;
import vn.jp.language.ljp.view.IClickListener;
import vn.jp.language.ljp.view.RecyclerTouchListener;
import vn.jp.language.ljp.view.purchase.IPurchase;
import vn.jp.language.ljp.view.purchase.PurchaseNewActivity;

/**
 * Created by huynhtd on 10/17/2016.
 */

public class PhraseActivity extends PurchaseNewActivity<PhraseActivity> implements SearchView.OnQueryTextListener, IPurchase {

    private final String TAG = "PhraseActivity";
    private final String FOLDER = "phrase/";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<PhraseEntity> listData;

    PhraseAdapter adapter;
    PhrasePresenter presenter;
    AudioPlayerManager audio;



    @Override
    protected int getLayout() {
        return R.layout.phrase_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "onCreate");

        presenter = new PhrasePresenter(this);
        adapter = new PhraseAdapter();
        audio = new AudioPlayerManager(this);

        setTitle(getString(R.string.title_phrase));
        initControl();
        loadData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_kanji, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
//        searchView.onActionViewExpanded();
        if (android.os.Build.VERSION.SDK_INT >= 17)
            searchView.setPaddingRelative(30, 0, 0, 0);
        else
            searchView.setPadding(15, 0, 0, 0);

        searchView.setQueryHint(getString(R.string.menu_search_hint)); // fill in the search term by default
        searchView.clearFocus(); // close the keyboard on load
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // ============ OnQueryTextListener =============
    @Override
    public boolean onQueryTextSubmit(String query) {
        filter(query.trim());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText.trim());
        return false;
    }
    /// ========= end OnQueryTextListener ===============

    //////////////

    public void filter(final String text) {

        presenter.searchData(text, new ICallback<List<PhraseEntity>>() {
            @Override
            public void onCallback(List<PhraseEntity> data) {
                listData = data;
                adapter.setData(listData, text);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "fail err:" + err);
            }
        });
    }

    private void initControl() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        // Adding RecyclerView Divider / Separator
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //Add event
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new IClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i(TAG, "onClick row pos:" + listData.get(position).sound);
//                getItemPurchased();

                if (isPurchased || listData.get(position).getNum() < Constant.TRIAL) {
                    audio.play(FOLDER + listData.get(position).sound);
                } else {
                    //////////
                    Log.i(TAG, "===> buy!!!");
                    setBillingClient();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.i(TAG, "onLongClick row pos:" + listData.get(position).romaji);
            }
        }));
    }

    private void loadData() {
        presenter.loadData(new ICallback<List<PhraseEntity>>() {
            @Override
            public void onCallback(List list) {
                listData = list;
                adapter.setData(listData, "");
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail!!!!!!" + err);
            }
        });
    }


    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> purchases) {
        Log.i(TAG, "onPurchasesUpdated....");
        //if item newly purchased
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                && purchases != null) {
            Log.i(TAG, "onPurchasesUpdated....Da mua");

            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
            isPurchased = false;
            Log.i(TAG, "onPurchasesUpdated....USER_CANCELED");
        } else {
            // Handle any other error codes.
//            isPurchased = false;
            Log.i(TAG, "onPurchasesUpdated....Error");

        }
    }


    //interface IPurchase
    @Override
    public void onCheckPurchase(boolean isPurchased) {
        this.isPurchased = isPurchased;
        if (isPurchased) {
            Log.i(TAG, "onCheckPurchase isPurchased:" + isPurchased);
            if (adapter != null) {
                adapter.setPurchased(isPurchased);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }else{
                Log.i(TAG, "onCheckPurchase, Adapter null; isPurchased:" + isPurchased);
            }
        } else {
            Log.i(TAG, "onCheckPurchase chua mua, isPurchased:" + isPurchased);
        }
    }
}
