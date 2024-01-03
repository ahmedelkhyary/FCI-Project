package com.collegemangement.fci.UploadMaterial;


import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.createPost;
import com.collegemangement.fci.ClassesModel.uploadMaterial;
import com.collegemangement.fci.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadPresenter {

    private HomeView view;

     UploadPresenter(HomeView view) {
        this.view = view;
    }



    public void UploadPresenter(Map<String, RequestBody> map, RequestBody id,
                                RequestBody description,RequestBody type, RequestBody level) {

        view.showLoading();
        Call<uploadMaterial> call = Utils.getApi().uploadMaterial(map , id , description, type ,level );
        call.enqueue(new Callback<uploadMaterial>() {

            @Override
            public void onResponse(Call<uploadMaterial> call, Response<uploadMaterial> response) {
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
            public void onFailure(Call<uploadMaterial> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }


        });
    }

}

