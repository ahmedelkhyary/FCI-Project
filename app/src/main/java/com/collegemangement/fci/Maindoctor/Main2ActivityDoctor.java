package com.collegemangement.fci.Maindoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.collegemangement.fci.About;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;
import com.collegemangement.fci.Createquiz.Createquiz;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.Doctors.doctors;
import com.collegemangement.fci.FirstGrade.Firstgrdae;
import com.collegemangement.fci.FourthGrade.fourthgrade;
import com.collegemangement.fci.GetQuizDoctor.GetQuizDoctor;
import com.collegemangement.fci.LoginApiActivity.Login;
import com.collegemangement.fci.ProfileInfromation.profile;
import com.collegemangement.fci.R;
import com.collegemangement.fci.Searchbydoctor.Searchbydoctor;
import com.collegemangement.fci.SecendGrade.secendgrdae;
import com.collegemangement.fci.ThirdGrade.thirdgrade;
import com.collegemangement.fci.Update.updatequiz;
import com.collegemangement.fci.UploadMaterial.uploadmaterial;
import com.collegemangement.fci.Events.events;
import com.collegemangement.fci.fourgradeis.fourgradeis;
import com.collegemangement.fci.getDoctorMaterial.GetDoctorMaterial;
import com.collegemangement.fci.message.message;
import com.collegemangement.fci.thirdgradeis.thirdgradeis;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main2ActivityDoctor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeView {

    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    MainDoctorPresenter mainDoctorPresenter;
    Helper helper;
    String cursor;
    TextView num;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_doctor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("FCI");

        name = findViewById(R.id.name);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), message.class));

            }
        });

        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        mainDoctorPresenter = new MainDoctorPresenter(this);
        mainDoctorPresenter.Getinfo(cursor);
        mainDoctorPresenter.unreadMessages(cursor);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        navigationView.setCheckedItem(R.id.message);
        navigationView.getMenu().getItem(1).setActionView(R.layout.messagenum);
        View x = navigationView.getMenu().getItem(1).getActionView();
        num = x.findViewById(R.id.num);


        List<String> list = new ArrayList<>();
        list.add("");
        list.add(" علـــــــوم ");
        list.add(" نـــظــــــم ");
        Spinner spinner = (Spinner) navigationView.getMenu().findItem(R.id.navigation_drawer_item3).getActionView();
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.preference_category, list));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(Main2ActivityDoctor.this,language[position],Toast.LENGTH_SHORT).show();
                if (position == 1)
                {
                    startActivity(new Intent(getApplicationContext(), thirdgrade.class));

                }else if (position == 2)
                {
                    startActivity(new Intent(getApplicationContext(), thirdgradeis.class));

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        spinner.getBackground().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);



        Spinner spinner2 = (Spinner) navigationView.getMenu().findItem(R.id.navigation_drawer_item4).getActionView();
        spinner2.setAdapter(new ArrayAdapter<String>(this,android.R.layout.preference_category, list));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(Main2ActivityDoctor.this,language[position],Toast.LENGTH_SHORT).show();
                if (position == 1)
                {
                    startActivity(new Intent(getApplicationContext(), fourthgrade.class));

                }else if (position == 2)
                {
                    startActivity(new Intent(getApplicationContext(), fourgradeis.class));

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.getBackground().setColorFilter(getResources().getColor(R.color.blue), PorterDuff.Mode.SRC_ATOP);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2_activity_doctor, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.student) {
            startActivity(new Intent(getApplicationContext(), Searchbydoctor.class));

        } else if (id == R.id.profile) {
            startActivity(new Intent(getApplicationContext(), profile.class));

        } else if (id == R.id.firstgrade) {
            startActivity(new Intent(getApplicationContext(), Firstgrdae.class));

        } else if (id == R.id.message) {
            startActivity(new Intent(getApplicationContext(), message.class));

        } else if (id == R.id.secendgrade) {
            startActivity(new Intent(getApplicationContext(), secendgrdae.class));

        }else if (id == R.id.quizes) {
            startActivity(new Intent(getApplicationContext(), GetQuizDoctor.class));


        } else if (id == R.id.update) {
            startActivity(new Intent(getApplicationContext(), updatequiz.class));


        } else if (id == R.id.event) {
            startActivity(new Intent(getApplicationContext(), events.class));


        } else if (id == R.id.materialdoc) {
            startActivity(new Intent(getApplicationContext(), GetDoctorMaterial.class));

        } else if (id == R.id.doctors) {
            startActivity(new Intent(getApplicationContext(), doctors.class));

        } else if (id == R.id.upload) {
            startActivity(new Intent(getApplicationContext(), uploadmaterial.class));

        } else if (id == R.id.quiz) {
            startActivity(new Intent(getApplicationContext(), Createquiz.class));

        } else if (id == R.id.logout) {

            sp = getApplicationContext().getSharedPreferences("login", 0);
            speditor = sp.edit();
            speditor.putBoolean("logged", false);
            speditor.apply();
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));

        }
        return false;
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
    public void info(studentOrDocInfo.User studentOrDocInfo) {


        name.setText("مرحبــا , " + studentOrDocInfo.getName());
        TextView nameofmenu = findViewById(R.id.nameofmenu);
        TextView email = findViewById(R.id.email);


        CircularImageView photoofmenu = findViewById(R.id.photoofmenu);
        String photo = studentOrDocInfo.getProfileImagePath();


        nameofmenu.setText(studentOrDocInfo.getName().toUpperCase());
        email.setText(studentOrDocInfo.getEmail());

        try {
            Picasso.get().load(photo).placeholder(R.drawable.demp).into(photoofmenu);

        } catch (Exception e) {

        }

    }

    @Override
    public void service(String result) {

    }

    @Override
    public void messagenum(String message) {
        num.setText(message);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:

                startActivity(new Intent(getApplicationContext(), About.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
