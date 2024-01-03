package com.collegemangement.fci.UpdataQuestion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.Quiz.quiz;
import com.collegemangement.fci.R;

public class UpdataQuestion extends AppCompatActivity implements HomeView {
    EditText question;
    EditText answer1;
    EditText answer2;
    EditText answer3;
    EditText answer4;
    TextView result;
    Button addquestion;
    ProgressBar progressBar;
    AlertDialog.Builder builderSingle;
    UpdataQuestionPresenter updataQuestionPresenter;
    Helper helper ;
    String cursor ;
    String idQuiz ;
    String idQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata_question);
        getSupportActionBar().setTitle("Update a Question ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updataQuestionPresenter = new UpdataQuestionPresenter(this);

        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        question = findViewById(R.id.question);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        result = findViewById(R.id.result);
        progressBar = findViewById(R.id.progressBar);
        addquestion = findViewById(R.id.addquestion);

        Intent intent = getIntent();
         idQuiz = intent.getStringExtra("idQuiz");
         idQuestion = intent.getStringExtra("idQuestion");

        String que = intent.getStringExtra("question");
        String ans1 = intent.getStringExtra("1");
        String ans2 = intent.getStringExtra("2");
        String ans3 = intent.getStringExtra("3");
        String ans4 = intent.getStringExtra("4");
        String answer = intent.getStringExtra("answer");


        question.setText(que);
        answer1.setText(ans1);
        answer2.setText(ans2);
        answer3.setText(ans3);
        answer4.setText(ans4);
        result.setText(answer);






        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builderSingle = new AlertDialog.Builder(UpdataQuestion.this);
                builderSingle.setIcon(R.drawable.logoapp);
                builderSingle.setTitle("Select One Grade:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UpdataQuestion.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("1");
                arrayAdapter.add("2");
                arrayAdapter.add("3");
                arrayAdapter.add("4");


                builderSingle.setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());

                builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
                    String x = arrayAdapter.getItem(which);
                    result.setText(x);
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdataQuestion.this);
                    builder.setMessage(x);
                    builder.setTitle(x);
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });


                });

                builderSingle.show();
            }


        });


        addquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String questionapp = question.getText().toString();
                String answer1app = answer1.getText().toString();
                String answer2app = answer2.getText().toString();
                String answer3app = answer3.getText().toString();
                String answer4app = answer4.getText().toString();
                String resultapp = result.getText().toString();

                if (questionapp.isEmpty())
                {
                    question.setError("عفوا هذا الحقل مطلوب");
                }else if (answer1app.isEmpty())
                {
                    answer1.setError("عفوا هذا الحقل مطلوب");
                }
                else if (answer2app.isEmpty())
                {
                    answer2.setError("عفوا هذا الحقل مطلوب");
                }
                else if (answer3app.isEmpty())
                {
                    answer3.setError("عفوا هذا الحقل مطلوب");
                }
                else if (answer4app.isEmpty())
                {
                    answer4.setError("عفوا هذا الحقل مطلوب");
                }
                else if (resultapp.isEmpty())
                {
                    result.setError("عفوا هذا الحقل مطلوب");
                }
                else
                {
                    updataQuestionPresenter.UpdataQuestion(questionapp, answer1app, answer2app, answer3app,
                            answer4app, resultapp, idQuestion,idQuiz ,cursor);

                }


            }
        });

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
    public void service(String message) {
        if (message.equals("question updated successfully"))
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
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



}
