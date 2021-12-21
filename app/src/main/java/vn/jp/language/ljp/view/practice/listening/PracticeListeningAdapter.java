package vn.jp.language.ljp.view.practice.listening;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Administrator on 7/21/2017.
 */

public class PracticeListeningAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PracticeListeningAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        PracticeListeningFragment tab = new PracticeListeningFragment();
        tab.pos = position;
        return tab;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}