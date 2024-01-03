package com.collegemangement.fci.fourgradeis;


import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.createPost;
import com.collegemangement.fci.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FourthGradeisPresenter {

    private HomeView view;

     FourthGradeisPresenter(HomeView view) {
        this.view = view;
    }

     void GetPosts(String id , String level) {

        view.showLoading();

        Call<PostsmodelBylevel> call = Utils.getApi().GetPostsByLeve(id ,level);
        call.enqueue(new Callback<PostsmodelBylevel>() {
            @Override
            public void onResponse(@NonNull Call<PostsmodelBylevel> call, @NonNull Response<PostsmodelBylevel> response) {
                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.posts(response.body().getPosts());

                }
            }

            @Override
            public void onFailure(Call<PostsmodelBylevel> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

    // Create a post

    public void Createpost(Map<String, RequestBody> map, RequestBody content, RequestBody id, RequestBody level, RequestBody posttype) {

        view.showLoading();
        Call<createPost> call = Utils.getApi().createPost(map , content , id ,level , posttype );
        call.enqueue(new Callback<createPost>() {

            @Override
            public void onResponse(Call<createPost> call, Response<createPost> response) {
                view.hideLoading();

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
                    view.service(response.body().getSuccess());

                }
            }

            @Override
            public void onFailure(Call<createPost> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }


        });
    }

}

