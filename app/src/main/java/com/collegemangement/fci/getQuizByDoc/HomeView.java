package com.collegemangement.fci.getQuizByDoc;


import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getquizByid;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void quiz(getquizByid.Quiz quiz);
    void result(String message);





}
