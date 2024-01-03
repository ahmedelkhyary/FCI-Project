package com.collegemangement.fci.thirdgradeis;


import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void posts(List<PostsmodelBylevel.Post> posts);
    void service(String result);


}
