package vn.jp.language.ljp.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class BasePresenter<T extends Activity> {
    public T activity;
    ProgressDialog progressDialog;


    public BasePresenter(T activity) {
        this.activity = activity;
        progressDialog = new ProgressDialog(activity);
    }

    public interface ILoadData {
        Object onBackground();
    }

    protected void loadData(ICallback iCallback, ILoadData iLoadData) {
        new ClsLoadData(iCallback, iLoadData).execute();
    }

    private class ClsLoadData extends AsyncTask<Void, Void, Object> {

        ILoadData iLoadData;
        ICallback iCallback;

        public ClsLoadData(ICallback iCallback, ILoadData iLoadData) {
            this.iLoadData = iLoadData;
            this.iCallback = iCallback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Void... params) {
            return iLoadData.onBackground();
        }

        @Override
        protected void onPostExecute(Object list) {
            super.onPostExecute(list);
            progressDialog.dismiss();
            iCallback.onCallback(list);
        }
    }


}
