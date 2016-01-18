package com.daniel.matters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dabraham on 1/8/16.
 */
public class MattersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Matter> matters;

    public MattersAdapter(Context context, List<Matter> matters) {
        this.context = context;
        this.matters = matters;
    }

    public void updateData(List<Matter> matters) {
        this.matters = matters;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_matters, null);

        MatterViewHolder viewHolder = new MatterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MatterViewHolder) {
            Matter matter = matters.get(position);
            String space = context.getResources().getString(R.string.matters_row_matter_id);

            ((MatterViewHolder) holder).setMatterIdText(String.format(space, matter.getId(), matter.getClientName()));
            Log.e("TAG", "client: " + String.format(space, matter.getId(), matter.getClientName()));
            ((MatterViewHolder) holder).setMatterDescriptionText(matter.getDescription());
            ((MatterViewHolder) holder).setMatter(matter);
        }
    }

    @Override
    public int getItemCount() {
        return matters.size();
    }
}
