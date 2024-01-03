package com.collegemangement.fci.Quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterQuiz;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterQuizOnlie;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getquiz;
import com.collegemangement.fci.ClassesModel.getquizByid;
import com.collegemangement.fci.Classmodeloflist.getquizoffline;
import com.collegemangement.fci.Classmodeloflist.result;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;
import com.collegemangement.fci.resultofquiz;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.material.internal.ViewUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class quiz extends AppCompatActivity implements HomeView {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    getquizPresenter getquizPresenter;
    String id;
    result result;
    TextView textView;
    String idofstudent;
    Helper helper;
    String cursor;
    String temp;
    TextView timerValue;
    private static final long START_TIME_IN_MILLIS = 1000000;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().setTitle("Quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        timerValue = findViewById(R.id.timerValue);

        result = new result(0);

        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        getquizPresenter = new getquizPresenter(this);


        Intent intent = getIntent();
        id = intent.getStringExtra("idQuiz");


        getquizPresenter.getQuiz(cursor, id);





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
    public void quiz(getquizByid.Quiz quiz) {


        ArrayList<getquizoffline> arrayList = new ArrayList<>();


        for (int i = 0; i < quiz.getQuestions().size(); i++) {


            arrayList.add(new getquizoffline(quiz.getQuestions().get(i).getQuestion(),
                    quiz.getQuestions().get(i).getOption1(),
                    quiz.getQuestions().get(i).getOption2(),
                    quiz.getQuestions().get(i).getOption3(),
                    quiz.getQuestions().get(i).getOption4(),
                    quiz.getQuestions().get(i).getAnswer()));


            RecyclerViewHomeAdapterQuizOnlie homeAdapter = new RecyclerViewHomeAdapterQuizOnlie(arrayList, this);
            recyclerView.setAdapter(homeAdapter);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                    GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(true);
            homeAdapter.notifyDataSetChanged();

             startTimer();

        }
    }

    @Override
    public void student(Studentmodel.Student student) {


    }

    @Override
    public void result(String result) {


        if (result.equals("The result was approved successfully")) {
            Intent intent = new Intent(getApplicationContext(), resultofquiz.class);
            intent.putExtra("result", temp);
            startActivity(intent);
        } else {
             Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }

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
    protected void onPause() {
        finish();
        getquizPresenter.sumitquiz(idofstudent, id, String.valueOf(result.getCount()));
        super.onPause();


    }


    private void startTimer() {
        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDowntText();
                temp = String.valueOf(result.getCount());

            }

            @Override
            public void onFinish() {

                getquizPresenter.sumitquiz(idofstudent, id, String.valueOf(result.getCount()));

            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDowntText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerValue.setText(timeLeft);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.submit:

                result.getCount();
                textView.setText(String.valueOf(result.getCount()));
                temp = String.valueOf(result.getCount());
                getquizPresenter.sumitquiz(cursor, id, String.valueOf(result.getCount()));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





}
