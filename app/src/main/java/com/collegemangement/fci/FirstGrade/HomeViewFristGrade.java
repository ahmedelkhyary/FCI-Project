package com.collegemangement.fci.FirstGrade;


import android.widget.ImageButton;

import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;

import java.util.List;

public interface HomeViewFristGrade {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void posts(List<PostsmodelBylevel.Post> posts);
    void service(String result);


}
