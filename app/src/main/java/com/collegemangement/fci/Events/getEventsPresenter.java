package com.collegemangement.fci.Events;

import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.getEvents;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.ClassesModel.studentsSearch;
import com.collegemangement.fci.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class getEventsPresenter {

    private HomeView view;

    public getEventsPresenter(HomeView view) {
        this.view = view;
    }

    void getEvents(){

        view.showLoading();

            view.showLoading();
            Call<getEvents> call = Utils.getApi().getEvents();
            call.enqueue(new Callback<getEvents>() {
                @Override
                public void onResponse(@NonNull Call<getEvents> call, @NonNull Response<getEvents> response) {

                    view.hideLoading();

                    if (response.isSuccessful() && response.body() != null) {

                        view.events(response.body().getEvents());

                    }
                }

                @Override
                public void onFailure(Call<getEvents> call, Throwable t) {
                    view.hideLoading();
                    view.onErrorLoading(t.getLocalizedMessage());

                }


            });
        }

}








