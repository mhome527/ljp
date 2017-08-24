package vn.jp.language.ljp.view.food;

import java.util.List;

import vn.jp.language.ljp.entity.WordEntity;
import vn.jp.language.ljp.view.BasePresenter;
import vn.jp.language.ljp.view.ICallback;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class FoodPresenter extends BasePresenter<FoodActivity> {


    public FoodPresenter(FoodActivity activity) {
        super(activity);
    }

    public void loadData(ICallback<List<WordEntity>> iCallback) {
        loadData(iCallback, new ILoadData() {
            @Override
            public List onBackground() {
                FoodDao dao = new FoodDao(activity);
                return dao.getListData();
            }
        });
    }

}
