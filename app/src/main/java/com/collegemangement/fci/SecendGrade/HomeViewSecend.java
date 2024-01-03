package com.collegemangement.fci.SecendGrade;


import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;

import java.util.List;

public interface HomeViewSecend {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void posts(List<PostsmodelBylevel.Post> posts);
    void service(String result);


}
