package com.collegemangement.fci.getQuizResult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterQuizdoctor;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterResultExam;
import com.collegemangement.fci.R;

import java.util.List;

public class getQuizResult extends AppCompatActivity implements HomeView {

    RecyclerView recyclerView ;
    ProgressBar progressBar;
    GetResultPresenter getResultPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quiz_result);

        getSupportActionBar().setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        getResultPresenter = new GetResultPresenter(this);
        getResultPresenter.GetResult(id);




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

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getQuizResult(List<com.collegemangement.fci.ClassesModel.getQuizResult.Result> results) {

        RecyclerViewHomeAdapterResultExam homeAdapter = new RecyclerViewHomeAdapterResultExam(results, this);
        recyclerView.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

    }
}
