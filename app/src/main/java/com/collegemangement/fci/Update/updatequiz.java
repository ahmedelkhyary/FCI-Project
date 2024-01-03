package com.collegemangement.fci.Update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterQuizdoctor;
import com.collegemangement.fci.ClassesModel.doctorUploadedQuizes;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.GetQuizDoctor.GetQuizDoctorPresenter;
import com.collegemangement.fci.Quiz.quiz;
import com.collegemangement.fci.R;
import com.collegemangement.fci.getQuizByDoc.getQuizByDoc;
import com.collegemangement.fci.getQuizResult.getQuizResult;

import java.util.List;

public class updatequiz extends AppCompatActivity implements HomeView {

    RecyclerView recyclerView ;
    ProgressBar progressBar;
    GetQuizPresenter GetQuizPresenter ;
    Helper helper ;
    String cursor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u);

        getSupportActionBar().setTitle("Update a Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        GetQuizPresenter = new GetQuizPresenter(this);
        GetQuizPresenter.GetQuizDoctor(cursor);



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
    public void doctorUploadedQuizes(List<doctorUploadedQuizes.Quize> quizes) {

        RecyclerViewHomeAdapterQuizdoctor homeAdapter = new RecyclerViewHomeAdapterQuizdoctor(quizes, this);
        recyclerView.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {


            Intent intent = new Intent(this, getQuizByDoc.class);
            intent.putExtra("idQuiz", quizes.get(position).getId());
            startActivity(intent);

        });



    }
}
