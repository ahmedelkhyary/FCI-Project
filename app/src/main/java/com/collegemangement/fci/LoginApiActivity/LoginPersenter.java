package com.collegemangement.fci.LoginApiActivity;

import android.util.Log;

import com.collegemangement.fci.ClassesModel.loginmodel;
import com.collegemangement.fci.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPersenter {

    private HomeView view;

    public LoginPersenter(HomeView view) {
        this.view = view;
    }

    void loginstudent(String email , String password , String name) {

        view.showLoading();

        Call<loginmodel> mealsCall = Utils.getApi().getresultofsginup(email , password , name);
        mealsCall.enqueue(new Callback<loginmodel>() {


            @Override
            public void onResponse(Call<loginmodel> call, Response<loginmodel> response) {
                view.hideLoading();

                if (response.code() == 404 || response.code() == 400 ) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");

                            view.service(message , message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                //Log.e("hh" , response.body().getMessage());

                if (response.isSuccessful() && response.body() != null) {

                    view.service(response.body().getMessage() , response.body().getUserId());
                    Log.d("RESPONSE" , response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<loginmodel> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }


        });
    }



}

