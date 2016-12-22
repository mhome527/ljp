package vn.jp.language.ljp.view.words;

import vn.jp.language.ljp.view.BasePresenter;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class WordPresenter extends BasePresenter<WordActivity> {


    public WordPresenter(WordActivity activity) {
        super(activity);
    }

//    public void loadData(int kind, ICallback iCallback) {
//        new ClsLoadData(kind, iCallback);
//    }
//
//    private class ClsLoadData extends AsyncTask<Void, Void, List> {
//        ICallback iCallback;
//        int kind;
//
//        public ClsLoadData(int kind, ICallback iCallback) {
//            this.iCallback = iCallback;
//            this.kind = kind;
//        }
//
//        @Override
//        protected List doInBackground(Void... params) {
//            WordDao dao = new WordDao(context);
//            return dao.getListData(kind);
//        }
//
//        @Override
//        protected void onPostExecute(List list) {
//            super.onPostExecute(list);
//
//            if (list != null || list.size() > 0)
//                iCallback.onCallback(list);
//            else
//                iCallback.onFail("unknown!!!");
//        }
//
//    }
}
