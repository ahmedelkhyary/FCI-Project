package com.collegemangement.fci.Chat;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.addMessage;
import com.collegemangement.fci.ClassesModel.createPost;
import com.collegemangement.fci.ClassesModel.getMessages;
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


public class ChatPresenter {

    private HomeView view;

    public ChatPresenter(HomeView view) {
        this.view = view;
    }

    void GetStudentInfo(String id){

        view.showLoading();

            view.showLoading();
            Call<studentOrDocInfo> call = Utils.getApi().studentOrDocInfo(id);
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


    public void Sendmessage(Map<String, RequestBody> map, RequestBody Recevier, RequestBody Sender, RequestBody Message, RequestBody Messagetype) {

        view.showLoading();
        Call<addMessage> call = Utils.getApi().addMessage(map , Recevier , Sender ,Message , Messagetype );
        call.enqueue(new Callback<addMessage>() {

            @Override
            public void onResponse(Call<addMessage> call, Response<addMessage> response) {
                view.hideLoading();

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");

                            view.addmessage(message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {
                    view.addmessage(response.body().getSuccess());

                }
            }

            @Override
            public void onFailure(Call<addMessage> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }


        });
    }




    void GetMessages(String idFirst , String idSecend){

        view.showLoading();

        view.showLoading();
        Call<getMessages> call = Utils.getApi().getMessages(idFirst , idSecend);
        call.enqueue(new Callback<getMessages>() {
            @Override
            public void onResponse(@NonNull Call<getMessages> call, @NonNull Response<getMessages> response) {

                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.messages(response.body().getMessages());

                }
            }

            @Override
            public void onFailure(Call<getMessages> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

    public void unreadMessages(String Currentuser , String secuser ){

        view.showLoading();

        view.showLoading();
        Call<unreadMessages> call = Utils.getApi().readedMessges(Currentuser , secuser);
        call.enqueue(new Callback<unreadMessages>() {
            @Override
            public void onResponse(@NonNull Call<unreadMessages> call, @NonNull Response<unreadMessages> response) {

                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.readedMessges(response.body().getUnreadMessages() );

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








