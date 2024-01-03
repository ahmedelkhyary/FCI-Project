package com.collegemangement.fci.Maindoctor;


import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.createPost;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;
import com.collegemangement.fci.ClassesModel.unreadMessages;
import com.collegemangement.fci.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainDoctorPresenter {

    private HomeView view;

     MainDoctorPresenter(HomeView view) {
        this.view = view;
    }

     void Getinfo (String id ) {

        view.showLoading();

        Call<studentOrDocInfo> call = Utils.getApi().studentOrDocInfo(id );
        call.enqueue(new Callback<studentOrDocInfo>() {
            @Override
            public void onResponse(@NonNull Call<studentOrDocInfo> call, @NonNull Response<studentOrDocInfo> response) {
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

    public void unreadMessages (  String id) {


        Call<unreadMessages> call = Utils.getApi().unreadMessages( id);
        call.enqueue(new Callback<unreadMessages>() {
            @Override
            public void onResponse(@NonNull Call<unreadMessages> call, @NonNull Response<unreadMessages> response) {


                if (response.isSuccessful() && response.body() != null) {

                    view.messagenum(response.body().getUnreadMessages() );

                }

            }


            @Override
            public void onFailure(Call<unreadMessages> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

}

