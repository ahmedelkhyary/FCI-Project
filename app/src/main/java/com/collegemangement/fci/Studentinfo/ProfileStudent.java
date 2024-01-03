package com.collegemangement.fci.Studentinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.collegemangement.fci.Chat.Chat;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

public class ProfileStudent extends AppCompatActivity implements HomeView {
    CircularImageView circularImageView;
    TextView name;
    TextView email;
    TextView level;
    TextView depart;
    TextView data;
    TextView gender;
    StudentPresenter studentPresenter ;
    Helper helper ;
    String cursor ;
    ImageView message ;
    String id ;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        studentPresenter = new StudentPresenter(this);

        helper = new Helper(getApplicationContext());
        cursor = helper.gettype();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        level = findViewById(R.id.level);
        depart = findViewById(R.id.depart);
        data = findViewById(R.id.data);
        gender = findViewById(R.id.gender);
        message = findViewById(R.id.sender);
        message.setVisibility(View.GONE);

        circularImageView = findViewById(R.id.photo);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        studentPresenter.GetStudentInfo(id);

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), Chat.class);
                intent1.putExtra("idResever" , id);
                startActivity(intent1);
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onErrorLoading(String message) {

    }

    @Override
    public void info(studentOrDocInfo.User info) {

        id = info.getId();

        message.setVisibility(View.VISIBLE);

        name.setText(info.getName());
        email.setText(info.getEmail());
        level.setText("Level "+info.getLevel());
        depart.setText(info.getDepartment());
        data.setText(" - / - / - ");
        gender.setText(info.getGender());
        try {
            Picasso.get().load(info.getProfileImagePath()).placeholder(R.drawable.demp).into(circularImageView);

        }catch (Exception e){}

    }


}
