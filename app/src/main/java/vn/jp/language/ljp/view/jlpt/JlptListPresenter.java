package vn.jp.language.ljp.view.jlpt;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.entity.JlptEntity;
import vn.jp.language.ljp.utils.Common;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Toaster;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;


/**
 * Created by Administrator on 7/17/2017.
 */

public class JlptListPresenter extends BasePresenter<JlptListActivity> {

    private static final String TAG = "JlptListPresenter";
    int level;

    JlptListDao dao;

    public JlptListPresenter(JlptListActivity activity, int level) {
        super(activity);
        this.level = level;
//        this.idRef = idRef;
        dao = new JlptListDao(activity, level);
    }


    public void loadData(ICallback iCallback) {

        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItems();
            }
        });
    }

    public void loadMondai(String test_date, int mondai, ICallback iCallback) {
        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItem(mondai, test_date);
            }
        });
    }

    public void downloadFile(JlptEntity item) {
//        String file_url = "https://firebasestorage.googleapis.com/v0/b/learnjapanese-966af.appspot.com/o/n2%2F12_2013_N2.mp3?alt=media&token=5d2b437b-d9bd-4dea-81e1-60aa6e0f9d14";
        new DownloadFileFromURL(item).execute(Constant.LINK_JLPT + item.link_download);
    }

    /**
     * Background Async Task to download file
     */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {
        JlptEntity item;

        public DownloadFileFromURL(JlptEntity item) {
            this.item = item;
        }

        /**
         * Before starting background thread Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//                    shoshowDialog(progress_bar_type);
            Log.i(TAG, "download start");
            activity.mProgressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lenghtOfFile = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);
                String path = Environment.getExternalStorageDirectory().toString();
                String folder = Constant.FOLDER_JLPT;
                File directory = new File(path + folder);
                if (!directory.exists()) {
                    directory.mkdir();
                    Log.i(TAG, "craete folder");
                }
                // Output stream
                OutputStream output = new FileOutputStream(path + Constant.FOLDER_JLPT + "/" + item.filename);

                byte data[] = new byte[1024 * 30];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            activity.mProgressDialog.setIndeterminate(false);
            activity.mProgressDialog.setMax(100);
            activity.mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String file_url) {
            Log.i(TAG, "download DONE");
            activity.mProgressDialog.dismiss();
            String path_file = Environment.getExternalStorageDirectory().toString() + Constant.FOLDER_JLPT + "/" + item.filename;
            if (Common.isExistFile(path_file)) {
                activity.startJlptListening(item);
            } else {
                new Toaster(activity).popToast("Download failed!");
            }
        }

    }

}
