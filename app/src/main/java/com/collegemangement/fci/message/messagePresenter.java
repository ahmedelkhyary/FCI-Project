package com.collegemangement.fci.message;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.messagesContacts;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.ClassesModel.studentsSearch;
import com.collegemangement.fci.ClassesModel.unreadMessages;
import com.collegemangement.fci.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class messagePresenter {

    private HomeView view;

    public messagePresenter(HomeView view) {
        this.view = view;
    }

    void messagesContacts(String id){

        view.showLoading();

            view.showLoading();
            Call<messagesContacts> call = Utils.getApi().messagesContacts(id);
            call.enqueue(new Callback<messagesContacts>() {
                @Override
                public void onResponse(@NonNull Call<messagesContacts> call, @NonNull Response<messagesContacts> response) {

                    view.hideLoading();

                    if (response.isSuccessful() && response.body() != null) {

                        view.messagesContacts(response.body().getContacts());

                    }
                }

                @Override
                public void onFailure(Call<messagesContacts> call, Throwable t) {
                    view.hideLoading();
                    view.onErrorLoading(t.getLocalizedMessage());

                }


            });
        }
    void SearchByKey(String key ,String id){

        view.showLoading();

        view.showLoading();
        Call<messagesContacts> call = Utils.getApi().contactsSearch(key,id);
        call.enqueue(new Callback<messagesContacts>() {
            @Override
            public void onResponse(@NonNull Call<messagesContacts> call, @NonNull Response<messagesContacts> response) {

                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.SearchBykey(response.body().getContacts());

                }
            }

            @Override
            public void onFailure(Call<messagesContacts> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

    public void unreadMessages(String Currentuser , String secuser , TextView textView){

        view.showLoading();

        view.showLoading();
        Call<unreadMessages> call = Utils.getApi().unreadMessagesBetween2Users(Currentuser , secuser);
        call.enqueue(new Callback<unreadMessages>() {
            @Override
            public void onResponse(@NonNull Call<unreadMessages> call, @NonNull Response<unreadMessages> response) {

                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.unreadMessagesBetween2Users(response.body().getUnreadMessages() , textView);

                }
            }

            @Override
            public void onFailure(Call<unreadMessages> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }
}








