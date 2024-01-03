package com.collegemangement.fci.getQuizResult;


import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.getQuizResult;
import com.collegemangement.fci.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetResultPresenter {

    private HomeView view;

    public GetResultPresenter(HomeView view) {
        this.view = view;
    }

    void  GetResult (String id ) {

        view.showLoading();

        Call<getQuizResult> call = Utils.getApi().getQuizResult(id);
        call.enqueue(new Callback<getQuizResult>() {
            @Override
            public void onResponse(@NonNull Call<getQuizResult> call, @NonNull Response<getQuizResult> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.getQuizResult(response.body().getResults());

                }
            }

            @Override
            public void onFailure(Call<getQuizResult> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

}

