package com.collegemangement.fci.ChangePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;

public class ChangePassword extends AppCompatActivity implements HomeView {

    EditText oldPassword ;
    EditText newPassword ;
    Button save ;
    updatepasswordPresenter updatepasswordPresenter ;
    Helper helper ;
    String cursor ;
    ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password2);
        getSupportActionBar().setTitle("Change password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        oldPassword = findViewById(R.id.oldpass);
        newPassword = findViewById(R.id.newpass);
        save = findViewById(R.id.save);
        progressBar = findViewById(R.id.progressBar);
        updatepasswordPresenter = new updatepasswordPresenter(this);
        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String old = oldPassword.getText().toString();
                String newpass = newPassword.getText().toString();

                if (old.length() <= 8)
                {
                    oldPassword.setError("كلمه السر قصيرة");
                }else  if (newpass.length() <= 8)
                {
                    newPassword.setError("كلمه السر قصيرة");
                }
                else
                {
                    updatepasswordPresenter.ChangePasswordStudent(old , newpass , cursor ,  "");
                }

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
    public void service(String message) {
        if (message.equals("user updated successfully")) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }
}
