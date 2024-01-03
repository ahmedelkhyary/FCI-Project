package com.collegemangement.fci.Payment;


import android.util.Log;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;
import com.collegemangement.fci.Utils;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentPresenter {

    private HomeView view;

    public PaymentPresenter(HomeView view) {
        this.view = view;
    }


    void Getinfo(String id) {

        view.showLoading();

        Call<studentOrDocInfo> call = Utils.getApi().studentOrDocInfo(id);
        call.enqueue(new Callback<studentOrDocInfo>() {
            @Override
            public void onResponse(@NonNull Call<studentOrDocInfo> call, @NonNull Response<studentOrDocInfo> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.info(response.body().getUser());

                }
            }

            @Override
            public void onFailure(Call<studentOrDocInfo> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }
}
