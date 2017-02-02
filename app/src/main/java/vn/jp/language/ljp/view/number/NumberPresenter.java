package vn.jp.language.ljp.view.number;

import java.util.ArrayList;
import java.util.List;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
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
            case NUMBER:
                return 1;
            case PERSON:
                return 2;
            case LONG:
                return 3;
            case THING:
                return 4;
            case BOOK:
                return 5;
            case ANIMAL:
                return 6;
            case AGE:
                return 7;
            case SMALL_OBJECT:
                return 8;
            case TIME:
                return 9;
            case LOCATION:
                return 10;
            case GENERIC:
                return 11;
            case MONTH:
                return 12;
            default:
                return 1;
        }
    }

    public List<String> getContentHeaderItem() {
        List<String> list = new ArrayList<>();
        list.add(activity.getString(R.string.number_count_number));
        list.add(activity.getString(R.string.number_count_month));
        list.add(activity.getString(R.string.number_count_person));
        list.add(activity.getString(R.string.number_count_long));
        list.add(activity.getString(R.string.number_count_flat));
        list.add(activity.getString(R.string.number_count_book));
        list.add(activity.getString(R.string.number_count_small_animal));
        list.add(activity.getString(R.string.number_count_age));
        list.add(activity.getString(R.string.number_count_small_object));
        list.add(activity.getString(R.string.number_count_time));
        list.add(activity.getString(R.string.number_count_location));
        list.add(activity.getString(R.string.number_count_generic));

        return list;
    }

    public List<String> getHeaderItem() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("月");
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
