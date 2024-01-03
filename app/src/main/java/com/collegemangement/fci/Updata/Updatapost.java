package com.collegemangement.fci.Updata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;

public class Updatapost extends AppCompatActivity implements HomeView {

    EditText content ;
    Button save ;
    ProgressBar progressBar;
    UpdatePresenter updatePresenter;
    Helper helper ;
    String cursor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatapost);

        getSupportActionBar().setTitle("Update");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updatePresenter = new UpdatePresenter(this);

        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        Intent intent = getIntent();
        String contentString = intent.getStringExtra("content");
        String id = intent.getStringExtra("id");

        content = findViewById(R.id.content);
        save = findViewById(R.id.save);
        progressBar = findViewById(R.id.progressBar);

        content.setText(contentString);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePresenter.Update(cursor ,id , content.getText().toString());

            }
        });

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

    }

    @Override
    public void Result(String results) {
        if (results.equals("post updated successfully"))
        {
            Toast.makeText(this, results, Toast.LENGTH_SHORT).show();
            finish();
        }else
        {
            Toast.makeText(this, results, Toast.LENGTH_SHORT).show();

        }

    }
}
