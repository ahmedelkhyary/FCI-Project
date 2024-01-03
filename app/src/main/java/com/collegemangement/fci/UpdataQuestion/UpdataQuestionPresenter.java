package com.collegemangement.fci.UpdataQuestion;

import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.addquestion;
import com.collegemangement.fci.ClassesModel.updateQuestion;
import com.collegemangement.fci.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdataQuestionPresenter {

    private HomeView view;

    public UpdataQuestionPresenter(HomeView view) {
        this.view = view;
    }

    void UpdataQuestion ( String question, String answer1, String answer2, String answer3, String answer4, String result,
                          String idQuestion,String idquiz , String idDoctor) {

        view.showLoading();

        Call<updateQuestion> Call = Utils.getApi().updateQuestion( question, answer1, answer2, answer3, answer4, result, idQuestion,idquiz, idDoctor);
        Call.enqueue(new Callback<updateQuestion>() {
            @Override
            public void onResponse(@NonNull Call<updateQuestion> call, @NonNull Response<updateQuestion> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.service(response.body().getMessage());

                }
            }

            @Override
            public void onFailure(Call<updateQuestion> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


}

