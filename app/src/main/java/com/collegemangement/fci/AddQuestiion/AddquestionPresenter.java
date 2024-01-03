package com.collegemangement.fci.AddQuestiion;

import com.collegemangement.fci.ClassesModel.addquestion;
import com.collegemangement.fci.Utils;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddquestionPresenter {

    private HomeView view;

    public AddquestionPresenter(HomeView view) {
        this.view = view;
    }

    void Addquestion ( String question, String answer1, String answer2, String answer3, String answer4, String result, String idquiz) {

        view.showLoading();

        Call<addquestion> mealsCall = Utils.getApi().addquestion( question, answer1, answer2, answer3, answer4, result, idquiz);
        mealsCall.enqueue(new Callback<addquestion>() {
            @Override
            public void onResponse(@NonNull Call<addquestion> call, @NonNull Response<addquestion> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.service(response.body().getMessage());

                }else
                {

                }
            }

            @Override
            public void onFailure(Call<addquestion> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


}

