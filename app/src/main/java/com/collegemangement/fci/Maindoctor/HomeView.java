package com.collegemangement.fci.Maindoctor;


import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void info (studentOrDocInfo.User studentOrDocInfo);
    void service(String result);
    void messagenum (String message);


}
