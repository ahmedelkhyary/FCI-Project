package com.collegemangement.fci.getQuizes;


import android.util.Log;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getdoctors;
import com.collegemangement.fci.ClassesModel.getquiz;
import com.collegemangement.fci.Utils;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class getquizPresenter {

    private HomeView view;

    public getquizPresenter(HomeView view) {
        this.view = view;
    }

    void getQuiz(String id) {

        view.showLoading();

        Call<getquiz> call = Utils.getApi().getQuizes(id);
        call.enqueue(new Callback<getquiz>() {
            @Override
            public void onResponse(@NonNull Call<getquiz> call, @NonNull Response<getquiz> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.quiz(response.body().getQuizes());

                }
            }

            @Override
            public void onFailure(Call<getquiz> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

}

