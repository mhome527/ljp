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
    String text = "";

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phrase_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PhraseEntity entity = list.get(position);
        holder.tvJp.setText(entity.getJp());
        holder.tvRomaji.setText(entity.getRomaji());
        holder.tvOt.setText(entity.getOt());
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvJp, tvRomaji, tvOt;

        public MyViewHolder(View view) {
            super(view);
            tvJp = (TextView) view.findViewById(R.id.tvJp1);
            tvRomaji = (TextView) view.findViewById(R.id.tvRomaji);
            tvOt = (TextView) view.findViewById(R.id.tvOt);
        }
    }

    public void setData(List<PhraseEntity> list, String text) {
        this.list = list;
        this.text = text;
    }
}
