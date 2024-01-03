package com.collegemangement.fci.ProfileInfromation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.collegemangement.fci.ChangePassword.ChangePassword;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class profile extends AppCompatActivity implements HomeView {

    CircularImageView imageprofile;
    TextView name;
    TextView email;
    TextView password;
    TextView department;
    TextView date;
    TextView grade;
    ProfilePresenter profilePresenter;
    Helper helper;
    String cursor;
    TextView gender;
    ImageView uploadimage ;
    LottieAnimationView progressBar;
    String mediaPath ;
    Button editPassword ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.cost);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        department = findViewById(R.id.department);
        date = findViewById(R.id.depart);
        grade = findViewById(R.id.grade);
        imageprofile = findViewById(R.id.circularImageView2);
        uploadimage = findViewById(R.id.uploadimage);

        gender = findViewById(R.id.gender);
        progressBar = findViewById(R.id.progressBar);
        editPassword = findViewById(R.id.editPassword);
        editPassword.setVisibility(View.GONE);
        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , ChangePassword.class));
            }
        });


        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();


        profilePresenter = new ProfilePresenter(this);
        profilePresenter.GetStudentInfo(cursor);


        uploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                        return;

                    }

                } else {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 0);
                }
            }


        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                imageprofile.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();

                uploadFile();

            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }


    }


    private void uploadFile() {


            Map<String, RequestBody> map = new HashMap<>();
            File file = new File(mediaPath);

            // Parsing any Media type file
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);

            RequestBody id = RequestBody.create(MediaType.parse("text/plain"),
                cursor);
            profilePresenter.updateProfileImage(map  , id);




    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 0);



                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
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
    public void info(studentOrDocInfo.User info) {

        String type = helper.gettype();
        if (type.equals("Student"))
        {
            editPassword.setVisibility(View.VISIBLE);


            String namelocal = info.getName();
            String emillocal = info.getEmail();
            String departlocal = info.getDepartment();
            String genderlocal = info.getGender();
            String photolocal = info.getProfileImagePath();
            String levelapp = info.getLevel();


            try {
                Picasso.get().load(photolocal).placeholder(R.drawable.demp).into(imageprofile);

            }catch (Exception e){}


            name.setText(namelocal);
            email.setText(emillocal);
            department.setText(departlocal);
             date.setText(" - / - / - ");
             gender.setText(genderlocal);
             grade.setText("Level " + levelapp);

        }else if (type.equals("Doctor"))
        {
            editPassword.setVisibility(View.VISIBLE);


            String namelocal = info.getName();
            String emillocal = info.getEmail();
            String genderlocal = info.getTitle();
            String photolocal = info.getProfileImagePath();



            try {
                Picasso.get().load(photolocal).placeholder(R.drawable.demp).into(imageprofile);

            }catch (Exception e){}


            name.setText(namelocal);
            email.setText(emillocal);
            date.setText(" - / - / - ");
            grade.setVisibility(View.GONE);
            department.setVisibility(View.GONE);
            gender.setText(genderlocal);
        }



    }


    @Override
    public void result(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

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
