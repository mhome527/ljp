package vn.jp.language.ljp.view.translate;

import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.view.BaseActivity;

public class TranslateActivity extends BaseActivity<TranslateActivity> {

    private final String TAG = "TranslateActivity";

    private WebView webView;
    ProgressBar pbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_translate;
    }

    @Override
    protected void initView() {
//        String url = "https://translate.google.com/?hl=%s#%s/vi/";
        String url = "https://translate.google.com/?hl=%25s&sl=ja&tl=en&op=translate";
        webView = findViewById(R.id.webView);
        pbar = findViewById(R.id.progressBar1);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDisplayZoomControls(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setWebChromeClient(new WebChromeClient() {
        });
        webView.setWebViewClient(new MCSWebViewClient());

//        url = String.format(url);
//        url = "http://google.com";
        webView.loadUrl(url);

    }


    private class MCSWebViewClient extends WebViewClient

    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            webView.loadUrl(url);
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pbar.setVisibility(View.GONE);
        }
    }

}
