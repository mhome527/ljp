package vn.jp.language.ljp.view.number;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.jp.language.ljp.Constant;

/**
 * Created by Administrator on 10/18/2016.
 */

public class NumberPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public NumberPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                NumberFragment tab1 = new NumberFragment();
                tab1.numbers = Constant.TYPE_NUMBERS.NUMBER;
                return tab1;
            case 1:
                NumberFragment tab2 = new NumberFragment();
                tab2.numbers = Constant.TYPE_NUMBERS.SHORT;
                return tab2;
           case 2:
                NumberFragment tab3 = new NumberFragment();
                tab3.numbers = Constant.TYPE_NUMBERS.LONG;
                return tab3;
           case 3:
                NumberFragment tab4 = new NumberFragment();
                tab4.numbers = Constant.TYPE_NUMBERS.ANIMAL;
                return tab4;
           case 4:
                NumberFragment tab5 = new NumberFragment();
                tab5.numbers = Constant.TYPE_NUMBERS.MACHINE;
                return tab5;
           case 5:
                NumberFragment tab6 = new NumberFragment();
                tab6.numbers = Constant.TYPE_NUMBERS.PERSON;
                return tab6;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
