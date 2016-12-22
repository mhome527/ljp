package vn.jp.language.ljp.view.number;

import java.util.ArrayList;
import java.util.List;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by HuynhTD on 12/21/2016.
 */

public class NumberPresenter extends BasePresenter<NumberActivity> {
    public NumberPresenter(NumberActivity activity) {
        super(activity);
    }

    public void loadData(final Constant.TYPE_NUMBERS numbers, ICallback callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public Object onBackground() {
                        NumberDao dao = new NumberDao(activity);
                        return dao.getListData(getKind(numbers));
                    }
                }
        );

    }

    private int getKind(Constant.TYPE_NUMBERS numbers) {
        switch (numbers) {
            case LONG:
                return 1;
            case SHORT:
                return 1;
            case PERSON:
                return 1;
            case ANIMAL:
                return 1;
            case MACHINE:
                return 1;
            default:
                return 1;
        }
    }

    public List<String> getHeaderItem(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("人");
        list.add("本");
        list.add("枚");
        list.add("冊");
        list.add("匹");
        list.add("歳");
        list.add("個");
        list.add("回");
        list.add("箇所");
        list.add("つ");
        return list;
    }
}
