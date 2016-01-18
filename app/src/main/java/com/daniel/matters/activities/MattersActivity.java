package com.daniel.matters.activities;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.daniel.matters.db.MatterDb;
import com.daniel.matters.events.MatterItemClickEvent;
import com.daniel.matters.utils.TimeToLive;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import retrofit2.Callback;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MattersActivity extends AppCompatActivity {

    private CompositeSubscription subscriptions;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.matters_list)
    RecyclerView mattersRecyclerView;

    MattersAdapter adapter;
    private List<Matter> matters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matters);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        EventBus.getDefault().register(this);

        // TODO: display a progress bar while fetching the matters

        getMatters();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscriptions != null) {
            subscriptions.unsubscribe();
            subscriptions = null;
        }
        EventBus.getDefault().unregister(this);
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

    private void openMatterDetails(Matter matter) {
        Intent intent = new Intent(this, MatterDetailsActivity.class);
        intent.putExtra("matter", matter);
        startActivity(intent);
    }

    /**
     * retrieve an updated list from the server if we figure they may be out of date
     * otherwise, load the list from the database
     */
    private void getMatters() {
        if (TimeToLive.updateMatters()) {
            getMattersFromServer();
        } else {
            loadMattersFromDb();
        }
    }

    private void loadMattersFromDb() {
        Observable<List<Matter>> observable = MatterDb.queryMatters();

        Subscription subscription = observable
                .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Matter>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "error loading matters from db: " + e.getMessage(), e);
                    }

                    @Override
                    public void onNext(List<Matter> matters) {
                        updateMattersAdapter(matters);
                    }
                });

        addSubscription(subscription);
    }

    private void addSubscription(Subscription subscription) {
        if (subscriptions == null) {
            subscriptions = new CompositeSubscription();
        }
        subscriptions.add(subscription);
    }

    private void getMattersFromServer() {
        ApiProvider.mattersApi().getMatters().enqueue(new Callback<MattersResponse>() {
            @Override
            public void onResponse(retrofit2.Response<MattersResponse> response) {
                if (response.body().matters != null) {
                    matters = response.body().matters;
                    MatterDb.insertMatters(matters);
                    updateMattersAdapter(response.body().matters);
                }

                TimeToLive.setLastMattersUpdateTime();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("TAG", "error getting matters from server: " + t.getMessage(), t);
                loadMattersFromDb();
            }
        });
    }

    private void updateMattersAdapter(List<Matter> matters) {
        if (adapter == null) {
            adapter = new MattersAdapter(this, matters);
            mattersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mattersRecyclerView.setAdapter(adapter);
        }
        adapter.updateData(matters);
    }

    @SuppressWarnings("unused")
    public void onEvent(MatterItemClickEvent event) {
        openMatterDetails(event.matter);
    }

}
