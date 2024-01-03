package com.collegemangement.fci.GetMaterial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterGetmaterial;
import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterStudent;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getMaterial;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.R;

import java.util.List;

public class getMateria extends AppCompatActivity implements HomeView {

    RecyclerView recyclerView ;
    ProgressBar progressBar;
    GetMaterialPresenter getMaterialPresenter ;
    Helper helper ;
    String cursor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_materia);


        getSupportActionBar().setTitle("Material");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        getMaterialPresenter = new GetMaterialPresenter(this);
        getMaterialPresenter.getstudentinfo(cursor);
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
    public void getMaterial(List<getMaterial.Material> getMaterial) {

        RecyclerViewHomeAdapterGetmaterial homeAdapter = new RecyclerViewHomeAdapterGetmaterial(getMaterial, this);
        recyclerView.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {




                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(getMaterial.get(position).getFilePath()));
                    startActivity(i);



        });

    }

    @Override
    public void student(Studentmodel.Student student) {

        getMaterialPresenter.getMaterial(student.getLevel());

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
