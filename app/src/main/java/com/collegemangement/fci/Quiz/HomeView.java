package com.collegemangement.fci.Quiz;


import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getquiz;
import com.collegemangement.fci.ClassesModel.getquizByid;

import java.util.List;


public interface HomeView {
    void showLoading();

    void hideLoading();

    void onErrorLoading(String message);

    void quiz(getquizByid.Quiz quiz);

    void student(Studentmodel.Student student);

    void result (String result);


}
