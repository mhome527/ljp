package vn.jp.language.ljp.view.practice.dashboard;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Administrator on 7/21/2017.
 */

public class PracticeDashboardAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PracticeDashboardAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        PracticeDashboardFragment tab = new PracticeDashboardFragment();
        tab.level = 5 - position;
        return tab;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}