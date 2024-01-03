package com.collegemangement.fci.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.R;

import java.util.List;

public class Forgotpassword extends AppCompatActivity implements HomeView {

    EditText email ;
    ForgetPasswordPresenter forgetPasswordPresenter ;
    Button send;
    String emaiapp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        email = findViewById(R.id.email);

        emaiapp = email.getText().toString();
        if (emaiapp.isEmpty())
        {
            email.setError("الرجاء ادخال الاميل");
        }
        forgetPasswordPresenter = new ForgetPasswordPresenter(this);
     //   forgetPasswordPresenter.getstudentinfo();
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "ahmedabdellatifzakimohamed@gmail.com"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SSSSs");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "FFF");
//emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text

               // startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
            }
        });

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void student(Studentmodel.Student student) {






        }

    }

