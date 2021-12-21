package vn.jp.language.ljp.view.date;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Administrator on 10/18/2016.
 */

public class DatePagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public DatePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                DateFragment tab1 = new DateFragment();
                tab1.page = 1;
                return tab1;
            case 1:
                DateFragment tab2 = new DateFragment();
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
