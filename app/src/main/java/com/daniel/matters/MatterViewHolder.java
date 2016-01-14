package com.daniel.matters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.daniel.matters.events.MatterItemClickEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by dabraham on 1/13/16.
 */
public class MatterViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    @Bind(R.id.matter_id)
    TextView matterId;

    @Bind(R.id.matter_description)
    TextView matterDescription;

    private Matter matter;

    public MatterViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
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
        Log.e("TAG", "on click");
        EventBus.getDefault().post(new MatterItemClickEvent(matter));
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }
}
