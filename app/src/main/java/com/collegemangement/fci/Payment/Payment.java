package com.collegemangement.fci.Payment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.MainPosts.MainActivity;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity;
import com.paytabs.paytabs_sdk.utils.PaymentParams;

import java.util.List;

public class Payment extends AppCompatActivity implements HomeView {

    Button pay;
    Intent intent;
    PaymentPresenter paymentPresenter;
    Helper helper;
    String cursor;
    TextView name ;
    TextView email ;
    TextView grade ;
    TextView cost ;
    ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);




        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        grade = findViewById(R.id.grade);
        cost = findViewById(R.id.cost);
        pay = findViewById(R.id.pay);
        progressBar = findViewById(R.id.progressBar2);

        getSupportActionBar().setTitle("الدفع الاكتروني");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pay.setVisibility(View.GONE);


        paymentPresenter = new PaymentPresenter(this);
        paymentPresenter.Getinfo(cursor);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double demo = Double.parseDouble(cost.getText().toString());
                Paymentprocess(demo);

            }
        });


        intent = new Intent(getApplicationContext(), MainActivity.class);

    }


    void Paymentprocess(double cost) {

        Intent in = new Intent(getApplicationContext(), PayTabActivity.class);
        in.putExtra(PaymentParams.MERCHANT_EMAIL, "ahmedabdellatifzakimohamed@gmail.com"); //receiver email
        in.putExtra(PaymentParams.SECRET_KEY, "4DF57CMJVikkHmoXukt4tiYfL9ozoE9tiPypsk8oDtIkP6ijrLoU3maPwyoUcynvoVURg3fTwXel4wFwi3qmqBbW9AQm8eRxaLoq");//Secret Key

        in.putExtra(PaymentParams.LANGUAGE, PaymentParams.ARABIC); //form language
        in.putExtra(PaymentParams.TRANSACTION_TITLE, "دفع المصاريف"); //transaction
        in.putExtra(PaymentParams.AMOUNT, cost); //costs


        in.putExtra(PaymentParams.CURRENCY_CODE, "USD"); //EGP not working in TEST MODE
        in.putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER, "00000000000"); //number not < 6
        in.putExtra(PaymentParams.CUSTOMER_EMAIL, "nothing@email.com");
        in.putExtra(PaymentParams.ORDER_ID, "123456");
        in.putExtra(PaymentParams.PRODUCT_NAME, "costs");

//Billing Address
        in.putExtra(PaymentParams.ADDRESS_BILLING, "nothing");
        in.putExtra(PaymentParams.CITY_BILLING, "nothing");
        in.putExtra(PaymentParams.STATE_BILLING, "nothing");
        in.putExtra(PaymentParams.COUNTRY_BILLING, "EGY");
        in.putExtra(PaymentParams.POSTAL_CODE_BILLING, "00000"); //Put Country Phone code if Postal code not available '00973'

//Shipping Address
        in.putExtra(PaymentParams.ADDRESS_SHIPPING, "nothing");
        in.putExtra(PaymentParams.CITY_SHIPPING, "nothing");
        in.putExtra(PaymentParams.STATE_SHIPPING, "nothing");
        in.putExtra(PaymentParams.COUNTRY_SHIPPING, "EGY");
        in.putExtra(PaymentParams.POSTAL_CODE_SHIPPING, "00000"); //Put Country Phone code if Postal code not available '00973'


//Payment Page Style
        in.putExtra(PaymentParams.PAY_BUTTON_COLOR, "#006797");

//Tokenization
        in.putExtra(PaymentParams.IS_TOKENIZATION, true);
        startActivityForResult(in, PaymentParams.PAYMENT_REQUEST_CODE);


    }

    // payment process activity (made with paytabs ) can be changed to egyptian payment service
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {

            if (data.getStringExtra(PaymentParams.RESPONSE_CODE).equals("100")) {

                Log.e("Payment", "okay");

//                if (payfor.contains("collegenew")) senddatatoserver();
                Toast.makeText(this, "تمت عمليه الدفع بنجاح", Toast.LENGTH_SHORT).show();
                startActivity(intent);

//
//                else {
//                    finish();
//                    startActivity(intent);
//                }
            } else {

                AlertDialog.Builder dialog = new AlertDialog.Builder(Payment.this);
                dialog.setTitle("حدث خطأ في الدفع حاول مرة أخري");
                dialog.setMessage("Paytabs ERROR CODE: " + data.getStringExtra(PaymentParams.RESPONSE_CODE));
                dialog.setCancelable(true);
                dialog.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //dismiss
                    }
                });
                dialog.create().show();

            }

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
    public void info(studentOrDocInfo.User studentOrDocInfo) {

        pay.setVisibility(View.VISIBLE);

        name.setText("Name - "+studentOrDocInfo.getName());
        email.setText("Email - "+studentOrDocInfo.getEmail());
        grade.setText( "Level - "+studentOrDocInfo.getLevel());

        if (studentOrDocInfo.getLevel().equals("1"))
        {
            cost.setText("50");
        }else if (studentOrDocInfo.getLevel().equals("2"))
        {
            cost.setText("60");
        }else
        {
            cost.setText("80");
        }



    }


}
