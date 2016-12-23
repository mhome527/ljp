package vn.jp.language.ljp.view.grammar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import vn.jp.language.ljp.utils.Log;

/**
 * Created by HuynhTD on 11/17/2016.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    static final private String TAG = "ViewPagerAdapter";

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.i(TAG, "ViewPagerAdapter");
    }

    @Override
    public Fragment getItem(int position) {
        Log.i(TAG, "getItem pos" + position);
        GrammarFragment fragment = new GrammarFragment();
//        return mFragmentList.get(position);
        switch (position) {
            case 1:
                fragment.level = 4;
                break;
            case 2:
                fragment.level = 5;
                break;
            case 3:
                fragment.level = 5;
                break;
             case 4:
                fragment.level = 5;
                break;
            default:
                fragment.level = 5;
                break;
        }
        return fragment;

    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
