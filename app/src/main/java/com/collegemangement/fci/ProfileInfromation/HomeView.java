package com.collegemangement.fci.ProfileInfromation;


import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void info(studentOrDocInfo.User info);
    void result (String s) ;

}
