package vn.jp.language.ljp.view.alphabet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.jp.language.ljp.Constant;

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
                tab1.alphabet = Constant.TYPE_ALPHABET.HIRAGANA;
                return tab1;
            case 1:
                AlphabetFragment tab2 = new AlphabetFragment();
                tab2.alphabet = Constant.TYPE_ALPHABET.KATAKANA;
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
