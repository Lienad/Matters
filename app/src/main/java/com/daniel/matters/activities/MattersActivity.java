package com.daniel.matters.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.daniel.matters.Matter;
import com.daniel.matters.MattersAdapter;
import com.daniel.matters.R;
import com.daniel.matters.apis.ApiProvider;
import com.daniel.matters.apis.MattersResponse;
import com.raizlabs.android.dbflow.runtime.TransactionManager;
import com.raizlabs.android.dbflow.runtime.transaction.SelectListTransaction;
import com.raizlabs.android.dbflow.runtime.transaction.TransactionListenerAdapter;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import retrofit2.Callback;

public class MattersActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.matters_list)
    ListView mattersListView;

    @SuppressWarnings("unused")
    @OnItemClick(R.id.matters_list) void showMatter(int position) {
        Matter matter = adapter.getItem(position);
        openMatterDetails(matter);
    }

    MattersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matters);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // TODO: display a progress bar while fetching the matters
        getMattersAsync();
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
                   List<Matter> matters = response.body().matters;
                   setupMattersList(matters);
                   saveMattersToDb(matters);
               }
           }

           @Override
           public void onFailure(Throwable t) {
               retrieveMattersFromDb();
           }
       });
    }

    private void setupMattersList(List<Matter> matters) {
        adapter = new MattersAdapter(this, R.layout.row_matters, matters);
        mattersListView.setAdapter(adapter);
    }

    private void retrieveMattersFromDb() {
        TransactionManager.getInstance().addTransaction(
                new SelectListTransaction<>(new Select().from(Matter.class),
                        new TransactionListenerAdapter<List<Matter>>() {
                            @Override
                            public void onResultReceived(List<Matter> matters) {
                                setupMattersList(matters);
                            }
                        }));
    }

    private void saveMattersToDb(List<Matter> matters) {
        TransactionManager.getInstance().saveOnSaveQueue(matters);
    }

    private void getMattersAsync() {
        new GetMattersTask().execute();
    }

    public class GetMattersTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            getMatters();
            return null;
        }
    }

    private void openMatterDetails(Matter matter) {
        Intent intent = new Intent(this, MatterDetailsActivity.class);
        intent.putExtra("matter", matter);
        startActivity(intent);
    }
}
