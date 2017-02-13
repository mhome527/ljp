package vn.jp.language.ljp.view.words;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.jp.language.ljp.Constant;

/**
 * Created by HuynhTD on 01/19/2017.
 */

public class WordPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    WordFragment tab1;
    WordFragment tab2;
    WordFragment tab3;

    public WordPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (tab1 == null)
                    tab1 = new WordFragment();
                tab1.typeWord = Constant.TYPE_WORD.ANIMAL;
                return tab1;
            case 1:
                if (tab2 == null)
                    tab2 = new WordFragment();
                tab2.typeWord = Constant.TYPE_WORD.FRUIT;
                return tab2;
            case 2:
                if (tab3 == null)
                    tab3 = new WordFragment();
                tab3.typeWord = Constant.TYPE_WORD.OTHER;
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
