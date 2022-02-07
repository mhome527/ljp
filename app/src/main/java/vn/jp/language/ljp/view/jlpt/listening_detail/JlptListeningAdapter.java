package vn.jp.language.ljp.view.jlpt.listening_detail;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Administrator on 7/21/2017.
 */

public class JlptListeningAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public JlptListeningAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        JlptListeningFragment tab = new JlptListeningFragment();
        tab.pos = position;
        return tab;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}