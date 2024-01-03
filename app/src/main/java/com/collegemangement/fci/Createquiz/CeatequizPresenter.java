package com.collegemangement.fci.Createquiz;

import android.util.Log;

import com.collegemangement.fci.ClassesModel.createQuizByDoc;
import com.collegemangement.fci.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CeatequizPresenter {

    private HomeView view;

    public CeatequizPresenter(HomeView view) {
        this.view = view;
    }

    void createQuizByDoc(String quizTitle, String level , String idDoctor ) {

        view.showLoading();

        Call<createQuizByDoc> mealsCall = Utils.getApi().createQuizByDoc(quizTitle, level, idDoctor);
        mealsCall.enqueue(new Callback<createQuizByDoc>() {
            @Override
            public void onResponse(@NonNull Call<createQuizByDoc> call, @NonNull Response<createQuizByDoc> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                        view.Result(response.body().getMessage() , response.body().getNewQuiz().getId());

                }


            }

            @Override
            public void onFailure(Call<createQuizByDoc> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
                Log.e("Error" , t.getLocalizedMessage().toString());

            }


        });
    }


}

