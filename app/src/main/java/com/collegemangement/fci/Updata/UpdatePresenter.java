package com.collegemangement.fci.Updata;


import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.getQuizResult;
import com.collegemangement.fci.ClassesModel.postUpdate;
import com.collegemangement.fci.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdatePresenter {

    private HomeView view;

    public UpdatePresenter(HomeView view) {
        this.view = view;
    }

    void  Update (String idUser , String idPost , String content) {

        view.showLoading();

        Call<postUpdate> call = Utils.getApi().postUpdate(idUser , idPost , content);
        call.enqueue(new Callback<postUpdate>() {
            @Override
            public void onResponse(@NonNull Call<postUpdate> call, @NonNull Response<postUpdate> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.Result(response.body().getMessage());

                }
            }

            @Override
            public void onFailure(Call<postUpdate> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

}

