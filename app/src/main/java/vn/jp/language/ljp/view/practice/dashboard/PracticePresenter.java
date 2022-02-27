package vn.jp.language.ljp.view.practice.dashboard;

import android.os.AsyncTask;
import android.os.Environment;

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
import java.nio.charset.StandardCharsets;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.db.DatabaseHelper;
import vn.jp.language.ljp.db.SqlLiteCopyDbHelper;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.view.BaseActivity;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.practice.list.PracticeDao;

/**
 * Created by Administrator on 7/7/2017.
 */

public class PracticePresenter extends BasePresenter<BaseActivity> {
    private static final String TAG = "PracticePresenter";

    PracticeDao dao;
    FirebaseStorage storage;
    StorageReference storageRef;

    public PracticePresenter(BaseActivity activity, int level, int kind) {
        super(activity);
        dao = new PracticeDao(activity, level, kind);
         storage = FirebaseStorage.getInstance();
         storageRef = storage.getReference();

    }

    public int countAll(int kind) {
        dao.setKind(kind);
        return dao.countAll();
    }

    public int countCorrect(int kind) {
        dao.setKind(kind);
        return dao.countCorrect();
    }

    public void downloadVersion(){
        StorageReference islandRef = storageRef.child("db/version.txt");

        final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                String removeVerDB = new String(bytes, StandardCharsets.UTF_8); // for UTF-8 encoding
                Log.i(TAG, "GET remote version db:" + removeVerDB);
                String localVerDB = activity.pref.getStringValue(Constant.DB_CURRENT, Constant.DB_CURRENT);
                if(removeVerDB.equals(localVerDB)){
                    Log.i(TAG, "version same===========");
                }else{
                    Log.i(TAG, "version diff, ======> download");
                    downloadDB(removeVerDB);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.i(TAG, "ERROR!!!!!! ");
                exception.printStackTrace();
            }
        });
    }

    public void downloadDB(String versionDB){
        StorageReference islandRef = storageRef.child("db/ljp4.sqlite");
        activity.pref.putIntValue(1, Constant.DB_DOWNLOADING);

        final long ONE_MEGABYTE = 1024 * 1024*20;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Log.i(TAG, "download DB DONE, ....");

                try {
                    DatabaseHelper.getInstance(activity).closeConnecion();
                    // Path to the just created empty db
                    String outFileName = activity.getApplicationInfo().dataDir + SqlLiteCopyDbHelper.DB_PATH_SUFFIX
                            + Constant.DB_NAME;

                    // if the path doesn't exist first, create it
                    File f = new File(activity.getApplicationInfo().dataDir + SqlLiteCopyDbHelper.DB_PATH_SUFFIX);
                    if (!f.exists())
                        f.mkdir();

                    // Open the empty db as the output stream
                    OutputStream myOutput = new FileOutputStream(outFileName);

                    // transfer bytes from the inputfile to the outputfile
//                    byte[] buffer = new byte[1024];
//                    int length;
//                    while ((length = myInput.read(buffer)) > 0) {
//                        myOutput.write(buffer, 0, length);
//                    }
                    myOutput.write(bytes);

                    // Close the streams
                    myOutput.flush();
                    myOutput.close();
                    activity.pref.putStringValue(versionDB, Constant.DB_CURRENT);
                    activity.pref.putIntValue(0, Constant.DB_DOWNLOADING);

                    Log.i(TAG, "Write DB DONE");
                } catch (Exception e) {
                    Log.i(TAG, "Download DE ERROR!!!!!! ");
                    activity.pref.putIntValue(0, Constant.DB_DOWNLOADING);
                    e.printStackTrace();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.i(TAG, "Download version ERROR!!!!!! ");
                exception.printStackTrace();
            }
        });
    }

    class DownloadVersion extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "download start");
        }
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

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
                OutputStream output = new FileOutputStream(path + Constant.FOLDER_JLPT + "/" );

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
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String file_url) {
            Log.i(TAG, "download DONE");

        }

    }
}
