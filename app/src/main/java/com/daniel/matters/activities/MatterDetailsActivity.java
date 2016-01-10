package com.daniel.matters.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.daniel.matters.Matter;
import com.daniel.matters.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dabraham on 1/8/16.
 */
public class MatterDetailsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.matter_id)
    TextView matterId;

    @Bind(R.id.client_name)
    TextView clientName;

    @Bind(R.id.matter_description)
    TextView matterName;

    @Bind(R.id.matter_open_date)
    TextView matterOpenDate;

    @Bind(R.id.matter_status)
    TextView matterStatus;

    Matter matter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matter_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        matter = getIntent().getParcelableExtra("matter");
        Log.e("TAG", "matter id: " + matter.getId());
        setFields();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_matters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
        case R.id.action_settings:
            return true;
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
    }

        return super.onOptionsItemSelected(item);
    }

    private void setFields() {
        matterId.setText(Long.toString(matter.getId()));
        clientName.setText(matter.getClientName());
        matterName.setText(matter.getDescription());
        matterOpenDate.setText(matter.getOpenDate());
        matterStatus.setText(matter.getStatus());
    }
}
