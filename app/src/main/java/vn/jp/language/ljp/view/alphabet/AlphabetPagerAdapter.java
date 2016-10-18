package vn.jp.language.ljp.view.alphabet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Administrator on 10/18/2016.
 */

public class AlphabetPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public AlphabetPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AlphabetFragment tab1 = new AlphabetFragment();
                tab1.page = 1;
                return tab1;
            case 1:
                AlphabetFragment tab2 = new AlphabetFragment();
                tab2.page = 2;
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
