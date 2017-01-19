//package vn.jp.language.ljp.view.words;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import java.util.List;
//
//import vn.jp.language.ljp.R;
//import vn.jp.language.ljp.entity.WordEntity;
//import vn.jp.language.ljp.utils.Utility;
//
///**
// * Created by HuynhTD on 10/17/2016.
// */
//
//public class WordAdapter extends BaseAdapter {
//
//    private static String TAG = "WordsAdapter";
//
//    Context context;
//    List<WordEntity> listData;
//    LayoutInflater layoutinflater;
////    int lang =0;
//
//    public WordAdapter(Context context, List<WordEntity> listData) {
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
//            convertView = layoutinflater.inflate(R.layout.word_item, parent, false);
//            holderView.img = (ImageButton) convertView.findViewById(R.id.imgWord);
//            holderView.tvJp1 = (TextView) convertView.findViewById(R.id.tvJp1);
//            holderView.tvOt = (TextView) convertView.findViewById(R.id.tvOt);
//            convertView.setTag(holderView);
//        } else {
//            holderView = (HolderView) convertView.getTag();
//        }
//
//
//        WordEntity entity = listData.get(position);
//        int resourceId = Utility.getResourcesID(context, entity.img);
//        holderView.img.setImageResource(resourceId);
//        holderView.tvJp1.setText(entity.jp1);
//        holderView.tvOt.setText(entity.ot);
//        return convertView;
//    }
//
//    static class HolderView {
//        ImageButton img;
//        TextView tvJp1;
//        //        TextView tvJp2;
//        TextView tvOt;
////        TextView tvRomaji;
//    }
//}
