package vn.jp.language.ljp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Administrator on 10/17/2016.
 */

public class BasePresenter {
    ProgressDialog progressDialog;
    public Context context;
//    ILoadData iLoadData;

    public interface ILoadData {
        List onBackground();
//        void onResponse(List list);
    }

    public BasePresenter(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }

    protected void loadData(ICallback iCallback, ILoadData iLoadData) {
        new ClsLoadData(iCallback, iLoadData).execute();
    }

    private class ClsLoadData extends AsyncTask<Void, Void, List> {

        ILoadData iLoadData;
        ICallback iCallback;
        public ClsLoadData(ICallback iCallback, ILoadData iLoadData){
            this.iLoadData = iLoadData;
            this.iCallback = iCallback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected List doInBackground(Void... params) {
            return iLoadData.onBackground();
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            progressDialog.dismiss();
            iCallback.onCallback(list);
        }
    }


}
