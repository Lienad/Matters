package com.daniel.matters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dabraham on 1/8/16.
 */
public class MattersAdapter extends ArrayAdapter<Matter> {

    @Bind(R.id.matter_id)
    TextView matterId;

    @Bind(R.id.matter_description)
    TextView matterDescription;

    public MattersAdapter(Context context, int resource, List<Matter> matters) {
        super(context, resource, matters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Matter matter = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_matters, parent, false);
        }

        ButterKnife.bind(this, convertView);

        String space = getContext().getResources().getString(R.string.matters_row_matter_id);

        matterId.setText(String.format(space, matter.getId(), matter.getClientName()));
        matterDescription.setText(matter.getDescription());

        return convertView;
    }
}
