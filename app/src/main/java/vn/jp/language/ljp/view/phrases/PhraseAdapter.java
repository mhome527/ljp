package vn.jp.language.ljp.view.phrases;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PhraseEntity;

/**
 * Created by Administrator on 10/18/2016.
 */

public class PhraseAdapter extends RecyclerView.Adapter<PhraseItemHolder> {

    List<PhraseEntity> list;
    String text = "";
    private boolean isPurchased = false;

    @Override
    public PhraseItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phrase_item, parent, false);

        return new PhraseItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhraseItemHolder holder, int position) {
       holder.bind(position, isPurchased, list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    public void setData(List<PhraseEntity> list, String text) {
        this.list = list;
        this.text = text;
    }

    public void setPurchased(boolean isPurchased){
        this.isPurchased = isPurchased;
    }
}
