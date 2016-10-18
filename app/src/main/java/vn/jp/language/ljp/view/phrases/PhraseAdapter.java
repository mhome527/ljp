package vn.jp.language.ljp.view.phrases;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.jp.language.ljp.R;
import vn.jp.language.ljp.entity.PhraseEntity;

/**
 * Created by Administrator on 10/18/2016.
 */

public class PhraseAdapter extends RecyclerView.Adapter<PhraseAdapter.MyViewHolder> {

    List<PhraseEntity> list;

    public PhraseAdapter(List<PhraseEntity> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phrase_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PhraseEntity entity = list.get(position);
        holder.tvJp1.setText(entity.getJp1());
        holder.tvJp2.setText(entity.getJp2());
        holder.tvOt.setText(entity.getOt());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvJp1, tvJp2, tvOt;

        public MyViewHolder(View view) {
            super(view);
            tvJp1 = (TextView) view.findViewById(R.id.tvJp1);
            tvJp2 = (TextView) view.findViewById(R.id.tvJp2);
            tvOt = (TextView) view.findViewById(R.id.tvOt);
        }
    }

}
