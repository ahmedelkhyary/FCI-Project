package com.collegemangement.fci.CreatedoctorEmail;

import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.createDoc;
import com.collegemangement.fci.ClassesModel.signupmodel;
import com.collegemangement.fci.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreatedocoteEmailPresenter {

    private HomeView view;

    public CreatedocoteEmailPresenter(HomeView view) {
        this.view = view;
    }

    void Createdoctors(String name, String email, String city, String data, String gender , String password) {

        view.showLoading();

        Call<createDoc> mealsCall = Utils.getApi().createDoc(name, email, city, data, gender, password );
        mealsCall.enqueue(new Callback<createDoc>() {
            @Override
            public void onResponse(@NonNull Call<createDoc> call, @NonNull Response<createDoc> response) {
                view.hideLoading();
                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("messages");

                            view.service(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.service(response.body().getMessage());
                    //   Log.e("RESPONSE" , response.body().toString());

                }
            }

            @Override
            public void onFailure(Call<createDoc> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


}

