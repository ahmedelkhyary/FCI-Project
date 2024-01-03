package com.collegemangement.fci.getQuizByDoc;


import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.correctQuiz;
import com.collegemangement.fci.ClassesModel.getquizByid;
import com.collegemangement.fci.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class getquizBydoctorPresenter {

    private HomeView view;

    public getquizBydoctorPresenter(HomeView view) {
        this.view = view;
    }

    void getQuiz(String idStudent , String idQuiz) {

        view.showLoading();

        Call<getquizByid> call = Utils.getApi().getQuizByDoc(idStudent , idQuiz);
        call.enqueue(new Callback<getquizByid>() {
            @Override
            public void onResponse(@NonNull Call<getquizByid> call, @NonNull Response<getquizByid> response) {
                view.hideLoading();
                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");

                            view.result(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (response.isSuccessful() && response.body() != null) {

                    view.quiz(response.body().getQuiz());

                }
            }

            @Override
            public void onFailure(Call<getquizByid> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }




}

