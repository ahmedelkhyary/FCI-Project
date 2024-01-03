package com.collegemangement.fci.SignAvtivity;

import com.collegemangement.fci.ClassesModel.signupmodel;
import com.collegemangement.fci.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SginupPresenter {

    private HomeView view;

    public SginupPresenter(HomeView view) {
        this.view = view;
    }

    void getMeals(String name, String email, String password, String department, String date, String level, String gender) {

        view.showLoading();

        Call<signupmodel> mealsCall = Utils.getApi().getresultofsginup(name, email, password, department, date, level , gender);
        mealsCall.enqueue(new Callback<signupmodel>() {
            @Override
            public void onResponse(@NonNull Call<signupmodel> call, @NonNull Response<signupmodel> response) {
                view.hideLoading();
                //  Log.e("a" , response.body().toString());

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");

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
            public void onFailure(Call<signupmodel> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


}

