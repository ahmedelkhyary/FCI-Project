package com.collegemangement.fci.getDoctorMaterial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.collegemangement.fci.Adapter.RecyclerViewHomeAdapterGetmaterial;
import com.collegemangement.fci.ClassesModel.getMaterial;
import com.collegemangement.fci.Database.Helper;
import com.collegemangement.fci.GetMaterial.GetMaterialPresenter;
import com.collegemangement.fci.R;

import java.util.List;

public class GetDoctorMaterial extends AppCompatActivity implements HomeView {

    RecyclerView recyclerView ;
    ProgressBar progressBar;
    GetMaterialdDoctorPresenter getMaterialdDoctorPresenter ;
    Helper helper ;
    String cursor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_doctor_material);

        getSupportActionBar().setTitle("Material");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);



        helper = new Helper(getApplicationContext());
        cursor = helper.getAlldata();

        getMaterialdDoctorPresenter = new GetMaterialdDoctorPresenter(this);
        getMaterialdDoctorPresenter.getMaterial(cursor);

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

