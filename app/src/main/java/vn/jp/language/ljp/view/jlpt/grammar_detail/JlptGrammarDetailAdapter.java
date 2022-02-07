package vn.jp.language.ljp.view.jlpt.grammar_detail;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Administrator on 7/21/2017.
 */

public class JlptGrammarDetailAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public JlptGrammarDetailAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        JlptGrammarDetailFragment tab = new JlptGrammarDetailFragment();
        tab.pos = position;
        return tab;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}