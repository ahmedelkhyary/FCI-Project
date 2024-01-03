package com.collegemangement.fci.Createquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.AddQuestiion.Addquestion;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.LoginApiActivity.Login;
import com.collegemangement.fci.R;

import org.w3c.dom.Text;

import java.util.List;

public class Createquiz extends AppCompatActivity implements HomeView {

    EditText title ;
    TextView level ;
    Button ceatequiz ;
    CeatequizPresenter ceatequizPresenter ;
    ProgressBar progressBar ;
    AlertDialog.Builder builderSingle;
    Helper helper ;
    String cursor ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createquiz);

        getSupportActionBar().setTitle("Create quiz");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        title = findViewById(R.id.title);
        level = findViewById(R.id.level);
        progressBar = findViewById(R.id.progressBar);
        ceatequiz = findViewById(R.id.createquiz);
        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();



        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builderSingle = new AlertDialog.Builder(Createquiz.this);
                builderSingle.setIcon(R.drawable.logoapp);
                builderSingle.setTitle("Select One Grade:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Createquiz.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("1");
                arrayAdapter.add("2");
                arrayAdapter.add("3cs");
                arrayAdapter.add("3is");
                arrayAdapter.add("4cs");
                arrayAdapter.add("4is");


                builderSingle.setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());

                builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
                    String x = arrayAdapter.getItem(which);
                    level.setText(x);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Createquiz.this);
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



        ceatequizPresenter = new CeatequizPresenter(this);

        ceatequiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titleapp = title.getText().toString();
                String levelapp = level.getText().toString();


                ceatequizPresenter.createQuizByDoc(titleapp , levelapp , cursor );

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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext() , Addquestion.class));

    }

    @Override
    public void Result (String message  ,String idQuiz ) {
        if (message.equals("quiz created successfully"))
        {
            Toast.makeText(this,"تم انشاء الامتحان بنجاح", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext() , Addquestion.class);
            intent.putExtra("idQuiz"  , idQuiz);
            startActivity(intent);
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
        super.onPause();
    }
}
