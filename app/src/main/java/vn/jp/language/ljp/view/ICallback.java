package vn.jp.language.ljp.view;

import java.util.List;

/**
 * Created by Administrator on 10/17/2016.
 */

public interface ICallback {
    void onCallback(List list);
    void onFail(String err);
}
