package com.collegemangement.fci.Doctors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterDoctors;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterStudent;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterStudentKey;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterdocotr;
import com.collegemangement.fci.ClassesModel.getdoctors;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.Doctorview;
import com.collegemangement.fci.R;
import com.collegemangement.fci.Searchbydoctor.SearchDoctorPresenter;
import com.collegemangement.fci.Studentinfo.ProfileStudent;

import java.util.List;

public class doctors extends AppCompatActivity implements HomeView {

    DoctorsPresenter doctorsPresenter;
    RecyclerView recyclerView;
    LottieAnimationView progressBar;
    Helper helper;
    String cursor;
    EditText search;
    ImageView back;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);


        search = findViewById(R.id.searchtousers);
        back = findViewById(R.id.back);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        doctorsPresenter = new DoctorsPresenter(this);
        doctorsPresenter.getdoctors(cursor);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                      doctorsPresenter.getdoctorsByKey(search.getText().toString() , cursor );


                    return true;
                }
                return false;
            }
        });


        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (search.getRight() - search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                        search.setText("");

                        return true;
                    }
                }
                return false;
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

    }

    @Override
    public void doctors(List<getdoctors.Doctor> doctors) {

        RecyclerViewHomeAdapterdocotr homeAdapter = new RecyclerViewHomeAdapterdocotr(doctors, this);
        recyclerView.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {

            Intent intent = new Intent(this, ProfileStudent.class);
            intent.putExtra("id", doctors.get(position).getId());

            startActivity(intent);
        });



    }
}


