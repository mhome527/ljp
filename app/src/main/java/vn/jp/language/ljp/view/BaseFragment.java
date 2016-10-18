package vn.jp.language.ljp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.jp.language.ljp.R;

/**
 * Created by Administrator on 10/18/2016.
 */

abstract public class BaseFragment extends Fragment {

    abstract public int getLayout();

    abstract public void initView(View root);

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.alphabet_sub1_layout, container, false);
        initView(root);
        return root;
    }

    protected <V extends View> V getView(int id) {
        return (V) root.findViewById(id);
    }


}
