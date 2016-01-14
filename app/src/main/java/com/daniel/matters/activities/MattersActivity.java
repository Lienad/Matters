package com.daniel.matters.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.daniel.matters.Matter;
import com.daniel.matters.MattersAdapter;
import com.daniel.matters.R;
import com.daniel.matters.apis.ApiProvider;
import com.daniel.matters.apis.MattersResponse;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Callback;

public class MattersActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.matters_list)
    RecyclerView mattersRecyclerView;

//    @SuppressWarnings("unused")
//    @OnItemClick(R.id.matters_list) void showMatter(int position) {
//        Matter matter = adapter.(position);
//        openMatterDetails(matter);
//    }

    MattersAdapter adapter;
    private List<Matter> matters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matters);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // TODO: display a progress bar while fetching the matters
        getMatters();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_matters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getMatters() {
       ApiProvider.mattersApi().getMatters().enqueue(new Callback<MattersResponse>() {
           @Override
           public void onResponse(retrofit2.Response<MattersResponse> response) {
               if (response.body().matters != null) {
                   matters = response.body().matters;
                   Log.e("TAG", "matters.size(): " + matters.size());
                   setupMattersList(response.body().matters);
               }
           }

           @Override
           public void onFailure(Throwable t) {
               // TODO: handle the error
           }
       });
    }

    private void setupMattersList(List<Matter> matters) {
        this.matters = matters;
        adapter = new MattersAdapter(this, matters);
        mattersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mattersRecyclerView.setAdapter(adapter);
    }

    private void openMatterDetails(Matter matter) {
        Intent intent = new Intent(this, MatterDetailsActivity.class);
        intent.putExtra("matter", matter);
        startActivity(intent);
    }
}
