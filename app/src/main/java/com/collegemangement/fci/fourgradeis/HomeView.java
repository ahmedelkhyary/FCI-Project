package com.collegemangement.fci.fourgradeis;


import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void posts(List<PostsmodelBylevel.Post> posts);
    void service(String result);


}
