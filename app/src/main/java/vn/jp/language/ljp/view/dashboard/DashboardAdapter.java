package vn.jp.language.ljp.view.dashboard;

import android.view.View;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.DashboardEntity;
import vn.jp.language.ljp.view.BaseAdapterView;

/**
 * Created by Administrator on 10/17/2016.
 */

public class DashboardAdapter extends BaseAdapterView<DashboardHolder> {

    private static String TAG = "DashboardAdapter";
    List<DashboardEntity> listData;

    public DashboardAdapter(List<DashboardEntity> listData) {
        this.listData = listData;
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
        return R.layout.dashboard_item;
    }

    @Override
    protected DashboardHolder getHeaderView(View view) {
        return null;
    }

    @Override
    protected DashboardHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected DashboardHolder getItemView(View view) {
        return new DashboardHolder(view);
    }

    @Override
    protected List getListData() {
        return listData;
    }

    @Override
    public void onBindViewHolder(DashboardHolder holder, int position) {
        holder.bind(listData.get(position));
    }

//    Context context;
//    LayoutInflater layoutinflater;
//
//    public DashboardAdapter(Context context, List<DashboardEntity> listData) {
//        this.context = context;
//        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        this.listData = listData;
//    }
//
//    @Override
//    public int getCount() {
//        return listData.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return listData.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        HolderView holderView;
//
//        if (convertView == null) {
//            holderView = new HolderView();
//            convertView = layoutinflater.inflate(R.layout.dashboard_item, parent, false);
//            holderView.imgItem = (ImageView) convertView.findViewById(R.id.imgItem);
//            holderView.tv = (TextView) convertView.findViewById(R.id.tvContent);
//            convertView.setTag(holderView);
//        }else{
//            holderView = (HolderView)convertView.getTag();
//        }
//
//        DashboardEntity entity = listData.get(position);
//        holderView.imgItem.setImageResource(entity.img);
//        holderView.tv.setText(entity.text);
//        return convertView;
//    }
//
//    static class HolderView {
//        ImageView imgItem;
//        TextView tv;
//    }
}
