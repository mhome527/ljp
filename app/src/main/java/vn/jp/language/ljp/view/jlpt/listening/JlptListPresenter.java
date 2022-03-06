package vn.jp.language.ljp.view.jlpt.listening;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.db.DatabaseHelper;
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
    JlptMstDao mstDao;
    FirebaseStorage storage;
    StorageReference storageRef;

    public JlptListPresenter(JlptListActivity activity, int level, int kind) {
        super(activity);
        this.level = level;
//        this.idRef = idRef;
        dao = new JlptListDao(activity, level);
        mstDao = new JlptMstDao(activity, level, kind);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
    }


    public void loadData(ICallback iCallback) {

        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return mstDao.getItems();
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
//        String link = Constant.LINK_JLPT + item.link_download;
//        Log.i(TAG, "url:" + link);
        String folder = "n" + item.level;

        StorageReference islandRef = storageRef.child(folder + "/" + item.filename);

//        new DownloadFileFromURL(item).execute(link);
        activity.mProgressDialog.setTitle("Downloading.....");
        activity.mProgressDialog.show();
//        activity.mProgressDialog.setIndeterminate(false);

        final long ONE_MEGABYTE = 1024 * 1024 * 30;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Log.i(TAG, "download DB DONE, ....");

                try {
                    DatabaseHelper.getInstance(activity).closeConnecion();

                    String path = Common.getPathFile(Constant.FOLDER_JLPT);
//                String folder = Constant.FOLDER_JLPT;
                    File dir = new File(path);
                    Log.i(TAG, "craete folder path:" + path);
                    if (!dir.exists()) {
                        boolean success = dir.mkdirs();
                        if (!success) {
                            Log.i(TAG, "craete folder success");
                        } else
                            Log.i(TAG, "craete folder FAIL!!!!!");
                    }
                    // Output stream
                    OutputStream output = new FileOutputStream(path + "/" + item.filename);

                    output.write(bytes);

                    // Close the streams
                    output.flush();
                    output.close();

                    Log.i(TAG, "Write DB DONE");
                    activity.mProgressDialog.dismiss();

                    if (Common.isExistFile(path)) {
                        activity.startJlptListening(item);
                    } else {
                        new Toaster(activity).popToast("Download failed!");
                    }
                } catch (Exception e) {
                    Log.i(TAG, "Download DE ERROR!!!!!! ");
                    activity.pref.putIntValue(0, Constant.DB_DOWNLOADING);
                    e.printStackTrace();
                    activity.mProgressDialog.dismiss();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.i(TAG, "Download file ERROR!!!!!! ");
                exception.printStackTrace();
                activity.mProgressDialog.dismiss();
            }
        });
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

                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);
                String path = Common.getPathFile(Constant.FOLDER_JLPT);
//                String folder = Constant.FOLDER_JLPT;
                File dir = new File(path);
                Log.i(TAG, "craete folder path:" + path);
                if (!dir.exists()) {
                    boolean success = dir.mkdirs();
                    if (!success) {
                        Log.i(TAG, "craete folder success");
                    } else
                        Log.i(TAG, "craete folder FAIL!!!!!");
                }
                // Output stream
                OutputStream output = new FileOutputStream(path + "/" + item.filename);

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
                e.printStackTrace();
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
            String path_file = Common.getPathFile(Constant.FOLDER_JLPT) + "/" + item.filename;
            if (Common.isExistFile(path_file)) {
                activity.startJlptListening(item);
            } else {
                new Toaster(activity).popToast("Download failed!");
            }
        }

    }

}
