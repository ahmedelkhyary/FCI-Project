package com.collegemangement.fci.LoginApiActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.CreatedoctorEmail.CreatedoctorEmail;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.ForgotPassword.Forgotpassword;
import com.collegemangement.fci.Maindoctor.Main2ActivityDoctor;
import com.collegemangement.fci.MainPosts.MainActivity;
import com.collegemangement.fci.R;
import com.collegemangement.fci.SignAvtivity.Signup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements HomeView {

    EditText email;
    EditText password;
    Button login;
    LoginPersenter loginPresenter ;
    ProgressBar progressBar ;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    TextView name ;
    AlertDialog.Builder builderSingle;
    String SelectType ;
    Helper helper ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_login);
        helper = new Helper(getApplicationContext());


        sp = getApplicationContext().getSharedPreferences("login", 0);
        speditor = sp.edit();
        if (sp.getBoolean("logged", false)) {

            String type = helper.gettype();
            if (type.equals("Student")) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else if (type.equals("Doctor"))
            {
                Intent intent = new Intent(this, Main2ActivityDoctor.class);
                startActivity(intent);
            }

        }

        email = findViewById(R.id.emailoflogin);
        password = findViewById(R.id.passoflogin);
        login = findViewById(R.id.login);
        progressBar = findViewById(R.id.prgressbar);
        name = findViewById(R.id.cost);

       // name.setInputType(InputType.TYPE_NULL);



        loginPresenter = new LoginPersenter(this);





        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builderSingle = new AlertDialog.Builder(Login.this);
                builderSingle.setIcon(R.drawable.logoapp);
                builderSingle.setTitle("Select One Name:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Login.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("Student");
                arrayAdapter.add("Doctor");


                builderSingle.setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());

                builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
                    String x = arrayAdapter.getItem(which);
                    name.setText(x);
                    SelectType = x ;
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
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





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailapp = email.getText().toString();
                String passapp = password.getText().toString();
                String nameapp = name.getText().toString();

                Boolean flag = isEmailValid(emailapp);

                if (!flag)
                    email.setError("Invaild Email");





                else if (emailapp.isEmpty())
                {
                    email.setError("الرجاء ادخال البيانات");
                }else if (passapp.isEmpty())
                {
                    password.setError("الرجاء ادخال البيانات");

                }else if (nameapp.isEmpty())
                {
                        name.setError("الرجاء ادخال البيانات");
                }
                else
                {
                    loginPresenter.loginstudent(emailapp ,passapp ,nameapp);
                }
            }
        });

    }



    public void signup(View view) {
        startActivity(new Intent(getApplicationContext() , CreatedoctorEmail.class));

    }

    public void x(View view) {
        startActivity(new Intent(getApplicationContext() , MainActivity.class));

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
        Toast.makeText(this, "الرجاء الاتصال بالانترنت", Toast.LENGTH_SHORT).show();



    }

    @Override
    public void service(String result ,String id) {


        if(result.equals("Success")){
           // Toast.makeText(this, "تمت اضافه البيانات بنجاح", Toast.LENGTH_SHORT).show();
            sp=getApplicationContext().getSharedPreferences("login", 0);
            speditor=sp.edit();
            speditor.putBoolean("logged",true);
            speditor.apply();

            Helper helper ;
            helper = new Helper(getApplicationContext());
            helper.insert( id);
            helper.inserttype(SelectType);

            if (SelectType.equals("Student")) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }else if (SelectType.equals("Doctor"))
            {
                Intent intent = new Intent(Login.this, Main2ActivityDoctor.class);
                startActivity(intent);
            }


        }else {
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

        }


    }


    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @Override
    protected void onPause() {
        this.finish();
        super.onPause();
    }


    public void forgot(View view) {
        startActivity(new Intent(getApplicationContext() , Forgotpassword.class));
    }
}
