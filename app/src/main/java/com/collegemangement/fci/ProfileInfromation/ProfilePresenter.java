package com.collegemangement.fci.ProfileInfromation;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.createPost;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;
import com.collegemangement.fci.ClassesModel.updateProfileImage;
import com.collegemangement.fci.Utils;
import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfilePresenter {

    private HomeView view;

    public ProfilePresenter(HomeView view) {
        this.view = view;
    }

    void GetStudentInfo(String id) {

        view.showLoading();
        Call<studentOrDocInfo> call = Utils.getApi().studentOrDocInfo(id);
        call.enqueue(new Callback<studentOrDocInfo>() {
            @Override
            public void onResponse(@NonNull Call<studentOrDocInfo> call, @NonNull Response<studentOrDocInfo > response) {

                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.info(response.body().getUser());

                }
            }

            @Override
            public void onFailure(Call<studentOrDocInfo> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

    void updateProfileImage(Map<String, RequestBody> map ,  RequestBody id) {

        view.showLoading();
        Call<updateProfileImage> call = Utils.getApi().updateProfileImage(map  , id);
        call.enqueue(new Callback<updateProfileImage>() {

            @Override
            public void onResponse(Call<updateProfileImage> call, Response<updateProfileImage> response) {
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
            public void onFailure(Call<updateProfileImage> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }


        });
    }
}




