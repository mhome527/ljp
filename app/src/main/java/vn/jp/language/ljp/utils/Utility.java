package vn.jp.language.ljp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;

public class Utility {
    private static String TAG = "Utility";
    public static int parseInt(final String num) {
        try {
            return Integer.parseInt(num);
        } catch (final Exception e) {
            return -1;
        }
    }

//    public static String ArrToString(String[] arr) {
//        String str = "";
//        if (arr == null || arr.length == 0)
//            return "";
//        if (arr.length == 1) {
//            str = arr[0];
//        } else {
//            for (int i = 0; i < arr.length - 1; i++) {
//                if (!arr[i].contains("ttt"))
//                    str += arr[i] + " - ";
//            }
//            str += arr[arr.length - 1];
//        }
//        return str;
//    }

//    public static AlertDialog dialogWifi(final Activity activity) {
//        // 1. Instantiate an AlertDialog.Builder with its constructor
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//
//        // create layout for dialog
//        LinearLayout layout = new LinearLayout(activity);
//        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setLayoutParams(parms);
//        layout.setGravity(Gravity.CLIP_VERTICAL);
//        layout.setPadding(2, 2, 2, 2);
//
//        TextView tv = new TextView(activity);
//        tv.setText(activity.getString(R.string.connect_wifi));
//        tv.setPadding(10, 30, 10, 30);
//        tv.setGravity(Gravity.CENTER);
//        tv.setTextSize(16);
//
//        // create layout for textview
//        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        layout.addView(tv, tvParams);
//
//        builder.setView(layout);
//        builder.setPositiveButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton(activity.getString(R.string.turn_on_wifi), new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User cancelled the dialog
//                dialog.dismiss();
//                activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//            }
//        });
//        // 3. Get the AlertDialog from create()
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        return dialog;
//    }

    // Check if Internet Network is active
    public static boolean checkNetwork(Activity activity) {
        boolean wifiDataAvailable = false;
        boolean mobileDataAvailable = false;
        ConnectivityManager conManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = conManager.getAllNetworkInfo();
        for (NetworkInfo netInfo : networkInfo) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    wifiDataAvailable = true;
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    mobileDataAvailable = true;
        }
        return wifiDataAvailable || mobileDataAvailable;
    }

    public static boolean createDirIfNotExists(String path) {
        boolean ret = true;

        File file = new File(path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("Utility", "Problem creating Image folder");
                ret = false;
            }
        }
        return ret;
    }

    public static int getResourcesID(Context context, String name) {
        try {
            if (name.equals(""))
                return -1;
            return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        } catch (Exception e) {
            Log.e("Utility", "get resource error:" + e.getMessage());
            return -1;
        }
    }

    public static int randomPos(int lenght, int... params) {
        Random r;
        int i;
        boolean b = false;

        for (int j = 0; j < 100; j++) {
            r = new Random();
            i = r.nextInt(lenght);
            for (int value : params) {
                if (i == value) {
                    b = false;
                    break;
                } else
                    b = true;
            }
            if (b)
                return i;
        }
        return 0;
    }

    public static boolean isSpeechRecognition(Context context) {
        try {
            // Check to see if a recognition activity is present
            PackageManager pm = context.getPackageManager();
            List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
            if (activities == null || activities.size() == 0) {
                Log.e(TAG, "checkSpeed NOT SUPORT!!!");
                return false;
            } else
                return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * Asking the permission for installing Google Voice Search. If permission granted – sent user to Google Play
     *
     * @param context – Activity, that initialized installing
     */
    public static void installGoogleVoiceSearch(final Context context) {

        // creating a dialog asking user if he want to install the Voice Search
        Dialog dialog = new AlertDialog.Builder(context).setMessage(context.getString(R.string.msg_recognition_voice))
                .setTitle(context.getString(R.string.msg_title_recognition))
                .setPositiveButton(context.getString(R.string.install), new DialogInterface.OnClickListener() {

                    // Install Button click handler
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            // creating an Intent for opening applications page in Google Play
                            // Voice Search package name: com.google.android.voicesearch
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PACKAGE_VOICE));
                            // setting flags to avoid going in application history (Activity call stack)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            // sending an Intent
                            context.startActivity(intent);
                        } catch (Exception ex) {
                            // if something going wrong doing nothing
                        }
                    }
                }).setNegativeButton(context.getString(R.string.cancel), null).create();

        dialog.show();
    }

    /**
     * Asking the permission for installing Premium app. If permission granted – sent user to Google Play
     *
     * @param context – Activity, that initialized installing
     */
    public static void installPremiumApp(final Context context) {

        // creating a dialog asking user if he want to install the Voice Search
        Dialog dialog = new AlertDialog.Builder(context).setMessage(context.getString(R.string.msg_premium_app))
                .setTitle(context.getString(R.string.msg_title_premium))
                .setPositiveButton(context.getString(R.string.install), new DialogInterface.OnClickListener() {

                    // Install Button click handler
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            // creating an Intent for opening applications page in Google Play
                            // Voice Search package name: com.google.android.voicesearch
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PACKAGE_PREMIUM));
                            // setting flags to avoid going in application history (Activity call stack)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            // sending an Intent
                            context.startActivity(intent);
                        } catch (Exception ex) {
                            // if something going wrong doing nothing
                        }
                    }
                }).setNegativeButton(context.getString(R.string.cancel), null).create();

        dialog.show();
    }

    /**
     * Asking the permission for installing Premium app. If permission granted – sent user to Google Play
     *
     * @param context – Activity, that initialized installing
     */
    public static void confirmUpdate(final Context context) {

        // creating a dialog asking user if he want to install the Voice Search
        Dialog dialog = new AlertDialog.Builder(context).setMessage(context.getString(R.string.msg_update_app))
                .setTitle(context.getString(R.string.msg_title_update))
                .setPositiveButton(context.getString(R.string.update), new DialogInterface.OnClickListener() {

                    // Install Button click handler
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            // creating an Intent for opening applications page in Google Play
                            // Voice Search package name: com.google.android.voicesearch
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PACKAGE_UPDATE));
                            // setting flags to avoid going in application history (Activity call stack)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            // sending an Intent
                            context.startActivity(intent);
                        } catch (Exception ex) {
                            // if something going wrong doing nothing
                        }
                    }
                }).setNegativeButton(context.getString(R.string.cancel), null).create();

        dialog.show();
    }

    public static void confirmCloseApp(final Activity activity) {

        // creating a dialog asking user if he want to install the Voice Search
        Dialog dialog = new AlertDialog.Builder(activity).setMessage(activity.getString(R.string.configm_replay))
                .setTitle(activity.getString(R.string.app_name))
                .setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {

                    // Install Button click handler
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            activity.finish();
                        } catch (Exception ex) {
                            // if something going wrong doing nothing
                        }
                    }
                }).setNegativeButton(activity.getString(R.string.cancel), null).create();

        dialog.show();
    }

    /**
     * Showing google speech input dialog
     */
    public static void promptSpeechInput(Activity activity, int result) {
        Log.i(TAG, "input locate: " + Locale.getDefault());
        if (!Utility.isSpeechRecognition(activity)) {
            Utility.installGoogleVoiceSearch(activity);
            return;
        }
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vi");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, activity.getString(R.string.speech_prompt));
        try {
            activity.startActivityForResult(intent, result);
        } catch (ActivityNotFoundException a) {
            Log.e(TAG, "input error:" + a.getMessage());
            a.printStackTrace();
            Toast.makeText(activity.getApplicationContext(), activity.getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Showing google speech input dialog
     */
    public static void promptSpeechInput(Activity activity, int result, String lang) {
        Log.i(TAG, "input locate: " + Locale.getDefault());
        if (!Utility.isSpeechRecognition(activity)) {
            Utility.installGoogleVoiceSearch(activity);
            return;
        }
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, lang);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, activity.getString(R.string.speech_prompt));
        try {
            activity.startActivityForResult(intent, result);
        } catch (ActivityNotFoundException a) {
            Log.e(TAG, "input error:" + a.getMessage());
            a.printStackTrace();
            Toast.makeText(activity.getApplicationContext(), activity.getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }

    public static String stripNonDigits(String input) {
        StringBuilder sb = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static long convertToLong(String number) {
        try {
            return Long.parseLong(number);
        } catch (Exception e) {
            return -1;
        }
    }

    public static int dpToPx(final float dp) {
        return Math.round(dp * (Resources.getSystem().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
