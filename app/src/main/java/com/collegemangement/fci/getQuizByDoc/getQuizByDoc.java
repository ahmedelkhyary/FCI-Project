package com.collegemangement.fci.getQuizByDoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterQuizOnlie;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterUpdate;
import com.collegemangement.fci.AddQuestiion.Addquestion;
import com.collegemangement.fci.ClassesModel.getquizByid;
import com.collegemangement.fci.Classmodeloflist.getquizoffline;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.Quiz.quiz;
import com.collegemangement.fci.R;
import com.collegemangement.fci.UpdataQuestion.UpdataQuestion;

import java.util.ArrayList;

public class getQuizByDoc extends AppCompatActivity implements HomeView {

    getquizBydoctorPresenter getquizBydoctorPresenter ;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Helper helper;
    String cursor;
    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_quiz_by_doc);


        getSupportActionBar().setTitle("Update a Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);


        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        Intent intent = getIntent();
        id = intent.getStringExtra("idQuiz");

        getquizBydoctorPresenter = new getquizBydoctorPresenter(this);
        getquizBydoctorPresenter.getQuiz(cursor , id);
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

    }

    @Override
    public void quiz(getquizByid.Quiz quiz) {


        ArrayList<getquizoffline> arrayList = new ArrayList<>();


        for (int i = 0; i < quiz.getQuestions().size(); i++) {


            arrayList.add(new getquizoffline(quiz.getQuestions().get(i).getQuestion(),
                    quiz.getQuestions().get(i).getOption1(),
                    quiz.getQuestions().get(i).getOption2(),
                    quiz.getQuestions().get(i).getOption3(),
                    quiz.getQuestions().get(i).getOption4(),
                    quiz.getQuestions().get(i).getAnswer()));


            RecyclerViewHomeAdapterUpdate homeAdapter = new RecyclerViewHomeAdapterUpdate(arrayList, this);
            recyclerView.setAdapter(homeAdapter);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                    GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(true);
            homeAdapter.notifyDataSetChanged();

            homeAdapter.setOnItemClickListener((view, position) -> {

                Intent intent = new Intent(getApplicationContext(), UpdataQuestion.class);

                intent.putExtra("idQuiz" , id);
                intent.putExtra("idQuestion" , quiz.getQuestions().get(position).getId());

                intent.putExtra("question" , quiz.getQuestions().get(position).getQuestion());
                intent.putExtra("1" , quiz.getQuestions().get(position).getOption1());
                intent.putExtra("2" , quiz.getQuestions().get(position).getOption2());
                intent.putExtra("3" , quiz.getQuestions().get(position).getOption3());
                intent.putExtra("4" , quiz.getQuestions().get(position).getOption4());
                intent.putExtra("answer" , quiz.getQuestions().get(position).getAnswer());
               startActivity(intent);


            });




        }
    }

    @Override
    public void result(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addquestion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addquestion:

                Intent intent = new Intent(getApplicationContext() , Addquestion.class);
                intent.putExtra("idQuiz"  , id);
                startActivity(intent);


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
