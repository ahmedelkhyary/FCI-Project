package com.collegemangement.fci.GetMaterial;


import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getMaterial;
import com.collegemangement.fci.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetMaterialPresenter {

    private HomeView view;

    public GetMaterialPresenter(HomeView view) {
        this.view = view;
    }

    void getMaterial(String level ) {

        view.showLoading();

        Call<getMaterial> call = Utils.getApi().getMaterial(level);
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


    public void getstudentinfo(String id) {


        Call<Studentmodel> call = Utils.getApi().getstudentinfo(id);
        call.enqueue(new Callback<Studentmodel>() {
            @Override
            public void onResponse(@NonNull Call<Studentmodel> call, @NonNull Response<Studentmodel> response) {

                if (response.isSuccessful() && response.body() != null) {

                    view.student(response.body().getStudent());

                }
            }

            @Override
            public void onFailure(Call<Studentmodel> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

}

