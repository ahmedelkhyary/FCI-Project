package com.collegemangement.fci.Quiz;


import android.util.Log;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.correctQuiz;
import com.collegemangement.fci.ClassesModel.getquiz;
import com.collegemangement.fci.ClassesModel.getquizByid;
import com.collegemangement.fci.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class getquizPresenter {

    private HomeView view;

    public getquizPresenter(HomeView view) {
        this.view = view;
    }

    void getQuiz(String idStudent , String idQuiz) {

        view.showLoading();

        Call<getquizByid> call = Utils.getApi().getquiz(idStudent , idQuiz);
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




    void sumitquiz(String idofstudent, String idofquiz, String result) {

        view.showLoading();

        Call<correctQuiz> call = Utils.getApi().correctQuiz(idofstudent, idofquiz, result);
        call.enqueue(new Callback<correctQuiz>() {
            @Override
            public void onResponse(@NonNull Call<correctQuiz> call, @NonNull Response<correctQuiz> response) {
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

                    view.result(response.body().getMessage());

                }

            }

            @Override
            public void onFailure(Call<correctQuiz> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


}

