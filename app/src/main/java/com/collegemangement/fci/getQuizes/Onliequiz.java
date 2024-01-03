package com.collegemangement.fci.getQuizes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterQuiz;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getquiz;
import com.collegemangement.fci.Createquiz.Createquiz;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;
import com.collegemangement.fci.Quiz.quiz;

import java.util.List;

public class Onliequiz extends AppCompatActivity implements HomeView {

    RecyclerView recyclerView ;
    ProgressBar progressBar ;
    getquizPresenter getquizPresenter ;
    Helper helper;
    String cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onliequiz);

        getSupportActionBar().setTitle("Online Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        getquizPresenter = new getquizPresenter(this);

        getquizPresenter.getQuiz(cursor);


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
    public void quiz(List<getquiz.Quize> quiz) {


        RecyclerViewHomeAdapterQuiz homeAdapter = new RecyclerViewHomeAdapterQuiz(quiz, this);
        recyclerView.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {


            Intent intent = new Intent(this, quiz.class);
            intent.putExtra("idQuiz", quiz.get(position).getId());
            startActivity(intent);


        });


    }

    @Override
    public void student(Studentmodel.Student student) {

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
    protected void onPause() {finish();
        super.onPause();
    }
}
