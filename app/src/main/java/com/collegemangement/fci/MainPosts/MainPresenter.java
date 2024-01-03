package com.collegemangement.fci.MainPosts;


import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.collegemangement.fci.ClassesModel.CreateComment;
import com.collegemangement.fci.ClassesModel.GetComments;
import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.comentReplay;
import com.collegemangement.fci.ClassesModel.commentLike;
import com.collegemangement.fci.ClassesModel.createPost;
import com.collegemangement.fci.ClassesModel.getReplayes;
import com.collegemangement.fci.ClassesModel.like;
import com.collegemangement.fci.ClassesModel.replayLike;
import com.collegemangement.fci.ClassesModel.unreadMessages;
import com.collegemangement.fci.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import androidx.annotation.NonNull;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPresenter {

    private HomeView view;

    public MainPresenter(HomeView view) {
        this.view = view;
    }

    // Method For Api " /studentPosts "

    public void GetAllStudentsLevel(String id , String level ) {

        view.showLoading();

        Call<PostsmodelBylevel> call = Utils.getApi().GetPostsByLeve(id , level);
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



    public void getstudentinfo(String id) {


        Call<Studentmodel> call = Utils.getApi().getstudentinfo(id);
        call.enqueue(new Callback<Studentmodel>() {
            @Override
            public void onResponse(@NonNull Call<Studentmodel> call, @NonNull Response<Studentmodel> response) {

                if (response.isSuccessful() && response.body() != null) {

                    view.student(response.body().getStudent());

                }
            }

            @Override
            public void onFailure(Call<Studentmodel> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void LikePost (String idStudent , String idPost, ImageView imageView , TextView textView
     , TextView textView1 , ImageView imageView1 , TextView textView2) {


        Call<like> call = Utils.getApi().like(idStudent , idPost);
        call.enqueue(new Callback<like>() {
            @Override
            public void onResponse(@NonNull Call<like> call, @NonNull Response<like> response) {

                if (response.isSuccessful() && response.body() != null) {

                    view.like(response.body().getMessage(), response.body().getLikesNum() , imageView , textView
                     , textView1 , imageView1 , textView2);


                }
            }

            @Override
            public void onFailure(Call<like> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void GetComments (String idPost , String IdStudent) {

        view.showLoading();

        Call<GetComments> call = Utils.getApi().GetComments(idPost, IdStudent);
        call.enqueue(new Callback<GetComments>() {
            @Override
            public void onResponse(@NonNull Call<GetComments> call, @NonNull Response<GetComments> response) {
                view.hideLoading();
                Log.e("P", "ok");

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.GetComments(response.body().getComments());

                }

            }


            @Override
            public void onFailure(Call<GetComments> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void CreateComment ( String content , String idPost , String IdStudent) {

        view.showLoading();

        Call<CreateComment> call = Utils.getApi().CreateComment(content , idPost, IdStudent);
        call.enqueue(new Callback<CreateComment>() {
            @Override
            public void onResponse(@NonNull Call<CreateComment> call, @NonNull Response<CreateComment> response) {
                view.hideLoading();
                Log.e("P", "ok");

                if (response.code() == 400) {
                    if (!response.isSuccessful()) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.errorBody().string());
                            String message = jsonObject.getString("message");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                if (response.isSuccessful() && response.body() != null) {

                    view.ResultOfComment(response.body().getMessage());

                }

            }


            @Override
            public void onFailure(Call<CreateComment> call, Throwable t) {

                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void LikeComment (  String idComment , String IdStudent  , ImageView imageView , TextView textView) {


        Call<commentLike> call = Utils.getApi().commentLike( idComment, IdStudent);
        call.enqueue(new Callback<commentLike>() {
            @Override
            public void onResponse(@NonNull Call<commentLike> call, @NonNull Response<commentLike> response) {


                if (response.isSuccessful() && response.body() != null) {

                    view.ResultOfLikeComment(response.body().getMessage() , response.body().getLikesNum() , imageView , textView);

                }

            }


            @Override
            public void onFailure(Call<commentLike> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void ComentReplay (  String idComment , String IdStudent  , String content) {


        Call<comentReplay> call = Utils.getApi().comentReplay( idComment, IdStudent , content);
        call.enqueue(new Callback<comentReplay>() {
            @Override
            public void onResponse(@NonNull Call<comentReplay> call, @NonNull Response<comentReplay> response) {


                if (response.isSuccessful() && response.body() != null) {

                    view.ResultOfCommentRelay(response.body().getMessage());

                }

            }


            @Override
            public void onFailure(Call<comentReplay> call, Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void getReplayes (  String idComment ) {

        view.showLoading();

        Call<getReplayes> call = Utils.getApi().getReplayes( idComment);
        call.enqueue(new Callback<getReplayes>() {
            @Override
            public void onResponse(@NonNull Call<getReplayes> call, @NonNull Response<getReplayes> response) {

                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.GetReplays(response.body().getReplayes());

                }

            }


            @Override
            public void onFailure(Call<getReplayes> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }


    public void replayLike (  String idReplay , String idStudent , ImageView imageView , TextView textView ) {


        Call<replayLike> call = Utils.getApi().replayLike( idReplay , idStudent);
        call.enqueue(new Callback<replayLike>() {
            @Override
            public void onResponse(@NonNull Call<replayLike> call, @NonNull Response<replayLike> response) {


                if (response.isSuccessful() && response.body() != null) {

                    view.ResultOfLikeReply(response.body().getMessage() , response.body().getLikesNum() , imageView , textView);

                }

            }


            @Override
            public void onFailure(Call<replayLike> call, Throwable t) {

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

