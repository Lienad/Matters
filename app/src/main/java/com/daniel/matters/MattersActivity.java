package com.daniel.matters;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.daniel.matters.apis.ApiProvider;

import java.util.List;

import retrofit2.Callback;

public class MattersActivity extends AppCompatActivity {

    private List<Matter> matters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getMattersAsync();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_matters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
               }
           }

           @Override
           public void onFailure(Throwable t) {
               // TODO: handle the error
           }
       });
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
}
