package com.collegemangement.fci.SignAvtivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.R;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity implements HomeView {

    EditText name ;
    EditText email ;
    EditText password ;
    EditText confirmpassword ;
    Button register ;
    SginupPresenter sginupPresenter ;
    ProgressBar progressBar;
    TextView department ;
    AlertDialog.Builder builderSingle;
    TextView date ;
    TextView gender ;
    TextView level ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.cost);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        progressBar = findViewById(R.id.prgressbar);
        register = findViewById(R.id.register);
        department = findViewById(R.id.department);
        date = findViewById(R.id.depart);

        gender = findViewById(R.id.gender);


        level = findViewById(R.id.level);

        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar=Calendar.getInstance();

                DatePickerDialog dpd= new DatePickerDialog(Signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day+"-"+(month+1)+"-"+year);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

                dpd.show();

            }
        });






        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builderSingle = new AlertDialog.Builder(Signup.this);
                builderSingle.setIcon(R.drawable.logoapp);
                builderSingle.setTitle("Select One Name:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Signup.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("Male");
                arrayAdapter.add("Female");


                builderSingle.setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());

                builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
                    String x = arrayAdapter.getItem(which);
                    gender.setText(x);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
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



        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builderSingle = new AlertDialog.Builder(Signup.this);
                builderSingle.setIcon(R.drawable.logoapp);
                builderSingle.setTitle("Select One level:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Signup.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("1");
                arrayAdapter.add("2");
                arrayAdapter.add("3is");
                arrayAdapter.add("3cs");
                arrayAdapter.add("4is");
                arrayAdapter.add("4cs");


                builderSingle.setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());

                builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
                    String x = arrayAdapter.getItem(which);
                    level.setText(x);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
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




        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builderSingle = new AlertDialog.Builder(Signup.this);
                builderSingle.setIcon(R.drawable.logoapp);
                builderSingle.setTitle("Select One Name:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Signup.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("General");
                arrayAdapter.add("Computer Science");
                arrayAdapter.add("Information System");


                builderSingle.setNegativeButton("cancel", (dialog, which) -> dialog.dismiss());

                builderSingle.setAdapter(arrayAdapter, (dialog, which) -> {
                    String x = arrayAdapter.getItem(which);
                    department.setText(x);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
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





        sginupPresenter = new SginupPresenter(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameapp = name.getText().toString();
                String emailapp = email.getText().toString();
                String passwordapp = password.getText().toString();
                String confirmpasswordapp = confirmpassword.getText().toString();
                String depratmentapp = department.getText().toString();
                String dataapp = date.getText().toString();
                String levelapp = level.getText().toString();

                String genderapp = gender.getText().toString();

                Boolean flag = isEmailValid(emailapp);

                if(!flag)
                    email.setError("Invalid Email");



                else if (nameapp.length()<=5)
                {
                    name.setError("الرجاء ادخال الاسم اكتر من خمسه احرف");
                }else if (emailapp.isEmpty() )
                {

                    email.setError("الرجاء ادخال البيانات");
                }

                else if (genderapp.isEmpty() )
                {
                    gender.setError("الرجاء ادخال البيانات");
                }
                else if (depratmentapp.isEmpty())
                {

                    department.setError("الرجاء ادخال البيانات");


                }
                else if (dataapp.isEmpty())
                {

                    date.setError("الرجاء ادخال البيانات");


                }
                else if (levelapp.isEmpty())
                {

                    level.setError("الرجاء ادخال البيانات");


                }

                else if (passwordapp.length()<= 8)
                {

                    password.setError("الرجاء ادخال بسورد اكثر من 8 احرف");


                }
                else if (confirmpasswordapp.equals(passwordapp))

                {
                    sginupPresenter.getMeals(nameapp , emailapp , passwordapp , depratmentapp , dataapp , levelapp, genderapp );

                }
                else {
                    Toast.makeText(Signup.this, "كلمه السر غير متتطابقه", Toast.LENGTH_SHORT).show();

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
        Toast.makeText(this, "الرجاء الاتصال بالانترنت", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void service(String result) {
        if(result.equals("Student added successfull ")){
            Toast.makeText(this, "تمت اضافه البيانات بنجاح", Toast.LENGTH_SHORT).show();

            name.setText("");
            email.setText("");
            password.setText("");
            confirmpassword.setText("");


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
}
