package vn.jp.language.ljp.view;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public interface ICallback<T> {
    void onCallback(T data);
    void onFail(String err);
}
