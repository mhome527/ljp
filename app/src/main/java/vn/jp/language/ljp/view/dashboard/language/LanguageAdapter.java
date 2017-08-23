package vn.jp.language.ljp.view.dashboard.language;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.jp.language.ljp.Constant;
import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.LanguageEntity;
import vn.jp.language.ljp.view.BaseAdapterView;

/**
 * Created by HuynhTD on 5/31/2017.
 */

public class LanguageAdapter extends BaseAdapterView<LanguageItemHolder> {

    List<LanguageEntity> items;
    String currLang;
    Context context;
    OnItemClickListener onItemClickListener;


    public LanguageAdapter(Context context, String currLang, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        initData(context, currLang);
    }

    private void initData(Context context, String currLang) {
        this.context = context;
        if (currLang == null || currLang.equals(""))
            this.currLang = Constant.EN;
        else
            this.currLang = currLang;

        createData();
    }

    @Override
    protected List getListData() {
        return items;
    }


    @Override
    protected int getHeaderLayout() {
        return 0;
    }

    @Override
    protected int getFooterLayout() {
        return 0;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.dialog_language_item;
    }

    @Override
    protected LanguageItemHolder getHeaderView(View view) {
        return null;
    }

    @Override
    protected LanguageItemHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected LanguageItemHolder getItemView(View view) {
        return new LanguageItemHolder(view);
    }

    @Override
    protected void onViewHolder(LanguageItemHolder holder, int position) {
        holder.setData(items.get(position), currLang, onItemClickListener);
    }

    ////
    private void createData() {
        items = new ArrayList<>();
        items.add(new LanguageEntity(Constant.EN, context.getString(R.string.english), R.drawable.english));
        items.add(new LanguageEntity(Constant.KO, context.getString(R.string.korea), R.drawable.korea));
        items.add(new LanguageEntity(Constant.FR, context.getString(R.string.france), R.drawable.france));
        items.add(new LanguageEntity(Constant.ZH, context.getString(R.string.chinese), R.drawable.china));
        items.add(new LanguageEntity(Constant.ES, context.getString(R.string.spanish), R.drawable.spanish));
    }

    public void setLang(String currLang) {
        this.currLang = currLang;
        notifyDataSetChanged();
    }

}
