package com.collegemangement.fci.MainPosts;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.collegemangement.fci.ClassesModel.GetComments;
import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getReplayes;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void posts(List<PostsmodelBylevel.Post> posts);
    void info(List<Postsmodel.PostCreator> info);
    void service(String result);
    void student(Studentmodel.Student student);
    void like(String idPsot , String idStudent , ImageView view , TextView textView ,
              TextView textView1 , ImageView imageView , TextView textView2);

    void GetComments(List<GetComments.Comment> GetComments);

    void ResultOfComment(String result);

    void ResultOfLikeComment(String result  , String noComment, ImageView imageView , TextView textView);

    void ResultOfCommentRelay(String result);

    void GetReplays (List<getReplayes.Replaye> GetReplays);

    void ResultOfLikeReply (String result  , String noComment, ImageView imageView , TextView textView);

    void messagenum (String message);



}
