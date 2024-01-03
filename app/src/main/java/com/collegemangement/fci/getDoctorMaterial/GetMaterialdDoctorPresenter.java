package com.collegemangement.fci.getDoctorMaterial;


import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getMaterial;
import com.collegemangement.fci.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetMaterialdDoctorPresenter {

    private HomeView view;

    public GetMaterialdDoctorPresenter(HomeView view) {
        this.view = view;
    }

    void getMaterial(String id ) {

        view.showLoading();

        Call<getMaterial> call = Utils.getApi().getDoctorMaterial(id);
        call.enqueue(new Callback<getMaterial>() {
            @Override
            public void onResponse(@NonNull Call<getMaterial> call, @NonNull Response<getMaterial> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.getMaterial(response.body().getMaterial());

                }
            }

            @Override
            public void onFailure(Call<getMaterial> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }



}

