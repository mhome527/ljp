package vn.jp.language.ljp.view.number;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.jp.language.ljp.Constant;

/**
 * Created by Administrator on 10/18/2016.
 */

public class NumberPagerAdapter extends FragmentStatePagerAdapter {
    Context context;
    int pages;

    public NumberPagerAdapter(Context context, FragmentManager fm, int pages) {
        super(fm);
        this.context = context;
        this.pages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        NumberFragment fragment = new NumberFragment();

        switch (position) {
            case 0:
                fragment.numbers = Constant.TYPE_NUMBERS.NUMBER;
                break;
            case 1:
                fragment.numbers = Constant.TYPE_NUMBERS.MONTH;
                break;
            case 2:
                fragment.numbers = Constant.TYPE_NUMBERS.PERSON;
                break;
            case 3:
                fragment.numbers = Constant.TYPE_NUMBERS.LONG;
                break;
            case 4:
                fragment.numbers = Constant.TYPE_NUMBERS.THING;
                break;
            case 5:
                fragment.numbers = Constant.TYPE_NUMBERS.BOOK;
                break;
            case 6:
                fragment.numbers = Constant.TYPE_NUMBERS.ANIMAL;
                break;
            case 7:
                fragment.numbers = Constant.TYPE_NUMBERS.AGE;
                break;
            case 8:
                fragment.numbers = Constant.TYPE_NUMBERS.SMALL_OBJECT;
                break;
            case 9:
                fragment.numbers = Constant.TYPE_NUMBERS.TIME;
                break;
            case 10:
                fragment.numbers = Constant.TYPE_NUMBERS.LOCATION;
                break;
            case 11:
            default:
                fragment.numbers = Constant.TYPE_NUMBERS.GENERIC;
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return pages;
    }
}
