package vn.jp.language.ljp.view.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.WordEntity;

/**
 * Created by Administrator on 10/18/2016.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodItemHolder> {

    List<WordEntity> list;
    String text = "";

    @Override
    public FoodItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);

        return new FoodItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FoodItemHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    public void setData(List<WordEntity> list, String text) {
        this.list = list;
        this.text = text;
    }

}
