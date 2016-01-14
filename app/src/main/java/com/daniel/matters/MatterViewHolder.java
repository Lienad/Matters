package com.daniel.matters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dabraham on 1/13/16.
 */
public class MatterViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    @Bind(R.id.matter_id)
    TextView matterId;

    @Bind(R.id.matter_description)
    TextView matterDescription;

    public MatterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setMatterIdText(String text) {
        matterId.setText(text);
    }

    public void setMatterDescriptionText(String text) {
        matterDescription.setText(text);
    }

    @Override
    public void onClick(View view) {
        // send click event
    }
}
