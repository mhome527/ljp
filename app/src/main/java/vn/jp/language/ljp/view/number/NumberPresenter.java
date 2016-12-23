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
            default:
                return 1;
        }
    }

    public String getNumberDescription(int position){
        List<String> stringList = getHeaderItem();
        switch (position) {
            case 0:
                return stringList.get(0);
            case 1:
                return stringList.get(1) + ": " + activity.getString(R.string.number_count_person);
            case 2:
                return stringList.get(2) + ": " +  activity.getString(R.string.number_count_long);
            case 3:
                return stringList.get(3) + ": " +  activity.getString(R.string.number_count_thing);
            case 4:
                return stringList.get(4) + ": " +  activity.getString(R.string.number_count_book);
            case 5:
                return stringList.get(5) + ": " +  activity.getString(R.string.number_count_animal);
            case 6:
                return stringList.get(6) + ": " +  activity.getString(R.string.number_count_age);
            case 7:
                return stringList.get(7) + ": " +  activity.getString(R.string.number_count_small_object);
            case 8:
                return stringList.get(8) + ": " +  activity.getString(R.string.number_count_time);
            case 9:
                return stringList.get(9) + ": " +  activity.getString(R.string.number_count_location);
            case 10:
                return stringList.get(10) + ": " +  activity.getString(R.string.number_count_generic);
            default:
                return stringList.get(0);
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
