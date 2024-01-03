package com.collegemangement.fci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class postdesgin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdesgin);


    }

    public static class resultofquiz extends AppCompatActivity {

        TextView result ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_resultofquiz);

            getSupportActionBar().setTitle("Result");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            result = findViewById(R.id.result);

            Intent intent= getIntent();
            String temp = intent.getStringExtra("result");
            result.setText(temp);
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
}
