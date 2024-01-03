package com.collegemangement.fci.ChangePassword;

import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.addquestion;
import com.collegemangement.fci.ClassesModel.updatepassword;
import com.collegemangement.fci.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class updatepasswordPresenter {

    private HomeView view;

    public updatepasswordPresenter(HomeView view) {
        this.view = view;
    }

    void ChangePasswordStudent ( String oldpassword, String newpassrod, String idStudent , String bio) {

        view.showLoading();

        Call<updatepassword> mealsCall = Utils.getApi().updatepassword( oldpassword, newpassrod, idStudent, bio);
        mealsCall.enqueue(new Callback<updatepassword>() {
            @Override
            public void onResponse(@NonNull Call<updatepassword> call, @NonNull Response<updatepassword> response) {
                view.hideLoading();

                if (response.code() == 404 || response.code() == 400 ) {
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

                }
            }

            @Override
            public void onFailure(Call<updatepassword> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


}

