package vn.jp.language.ljp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.utils.Log;
import vn.jp.language.ljp.utils.Prefs;

/**
 * Created by Administrator on 10/11/2016.
 */

public abstract class BaseActivity<T> extends AppCompatActivity {

    private static String TAG = "BaseActivity";

    protected abstract int getLayout();

    protected abstract void initView();

    protected T activity;

    public Prefs pref;
    public String lang = "";
    public int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(getLayout());
        ButterKnife.bind(this);
        activity = (T) this;

        if (pref == null)
            pref = new Prefs(getApplicationContext());
        lang = pref.getStringValue("", Constant.TYPE_LANGUAGE);

        if (lang.equals("")) {
            if (getString(R.string.language).equals(Constant.EN))
                lang = Constant.EN;
            else if (getString(R.string.language).equals(Constant.KO))
                lang = Constant.KO;
            else if (getString(R.string.language).equals(Constant.FR))
                lang = Constant.FR;
            else if (getString(R.string.language).equals(Constant.ZH))
                lang = Constant.ZH;
            else if (getString(R.string.language).equals(Constant.ES))
                lang = Constant.ES;
            else
                lang = Constant.EN;

        }

        initView();

    }


    public void startActivity2(Class<?> cls) {
        Intent i = new Intent(BaseActivity.this, cls);
        startActivity(i);
    }

    public void startActivity2(Class<?> cls, int param) {
        Intent i = new Intent(BaseActivity.this, cls);
        i.putExtra(Constant.INTENT_KIND, param);
        startActivity(i);
    }

    public void setTitleCenter(String title) {
        try {

            View view = getLayoutInflater().inflate(R.layout.custom_action_bar_layout, null);
            ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT,
                    ActionBar.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER);

            TextView Title = (TextView) view.findViewById(R.id.tvTitle);
            Title.setText(title);

            getSupportActionBar().setCustomView(view, params);
            getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
            getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title


        } catch (Exception e) {
            Log.e(TAG, "error333: " + e.getMessage());
        }
    }

}
