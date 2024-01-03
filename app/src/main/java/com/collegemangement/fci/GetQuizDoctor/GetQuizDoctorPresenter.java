package com.collegemangement.fci.GetQuizDoctor;


import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.doctorUploadedQuizes;
import com.collegemangement.fci.ClassesModel.getMaterial;
import com.collegemangement.fci.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetQuizDoctorPresenter {

    private HomeView view;

    public GetQuizDoctorPresenter(HomeView view) {
        this.view = view;
    }

    void GetQuizDoctor(String id ) {

        view.showLoading();

        Call<doctorUploadedQuizes> call = Utils.getApi().doctorUploadedQuizes(id);
        call.enqueue(new Callback<doctorUploadedQuizes>() {
            @Override
            public void onResponse(@NonNull Call<doctorUploadedQuizes> call, @NonNull Response<doctorUploadedQuizes> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.doctorUploadedQuizes(response.body().getQuizes());

                }
            }

            @Override
            public void onFailure(Call<doctorUploadedQuizes> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

}

