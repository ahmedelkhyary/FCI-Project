package com.collegemangement.fci.Events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterEvent;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterGetmaterial;
import com.collegemangement.fci.ClassesModel.getEvents;
import com.collegemangement.fci.R;

import java.util.List;

public class events extends AppCompatActivity implements HomeView {

    getEventsPresenter getEventsPresenter ;
    RecyclerView recyclerView ;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        getSupportActionBar().setTitle("Events");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getEventsPresenter = new getEventsPresenter(this);

        recyclerView = findViewById(R.id.recyclerView);
        lottieAnimationView=  findViewById(R.id.progressBar);

        getEventsPresenter.getEvents();


    }

    @Override
    public void showLoading() {
        lottieAnimationView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        lottieAnimationView.setVisibility(View.GONE);

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void events(List<getEvents.Event> getEvents) {

        RecyclerViewHomeAdapterEvent homeAdapter = new RecyclerViewHomeAdapterEvent(getEvents, this);
        recyclerView.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}