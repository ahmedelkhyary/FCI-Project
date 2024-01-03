package com.collegemangement.fci.ThirdGrade;


import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;

import java.util.List;

public interface HomeViewThird {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void posts(List<PostsmodelBylevel.Post> posts);
    void service(String result);


}
