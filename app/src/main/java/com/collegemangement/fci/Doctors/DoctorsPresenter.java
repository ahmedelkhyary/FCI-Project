package com.collegemangement.fci.Doctors;


import android.util.Log;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getdoctors;
import com.collegemangement.fci.Utils;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorsPresenter {

    private HomeView view;

    public DoctorsPresenter(HomeView view) {
        this.view = view;
    }

    void getdoctors(String id ) {

        view.showLoading();

        Call<getdoctors> mealsCall = Utils.getApi().getdoctors(id);
        mealsCall.enqueue(new Callback<getdoctors>() {
            @Override
            public void onResponse(@NonNull Call<getdoctors> call, @NonNull Response<getdoctors> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.doctors(response.body().getDoctors());

                }
            }

            @Override
            public void onFailure(Call<getdoctors> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }
    void getdoctorsByKey( String key , String id ) {

        view.showLoading();

        Call<getdoctors> mealsCall = Utils.getApi().getdoctorsbyKey(key , id);
        mealsCall.enqueue(new Callback<getdoctors>() {
            @Override
            public void onResponse(@NonNull Call<getdoctors> call, @NonNull Response<getdoctors> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.doctors(response.body().getDoctors());

                }
            }

            @Override
            public void onFailure(Call<getdoctors> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

}

