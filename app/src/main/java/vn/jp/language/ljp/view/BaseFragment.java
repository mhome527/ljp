package vn.jp.language.ljp.view;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import vn.jp.language.ljp.R;

/**
 * Created by Administrator on 10/18/2016.
 */

abstract public class BaseFragment<T>  extends Fragment {

    public T activity;

    abstract public int getLayout();

    abstract public void initView(View root);

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, root);
        activity = (T)getActivity();
        initView(root);
        return root;
    }

//    protected <V extends View> V getView(int id) {
//        return (V) root.findViewById(id);
//    }


}
