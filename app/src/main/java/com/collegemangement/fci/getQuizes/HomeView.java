package com.collegemangement.fci.getQuizes;


import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getquiz;

import java.util.List;


public interface HomeView {
    void showLoading();

    void hideLoading();

    void onErrorLoading(String message);

    void quiz (List<getquiz.Quize> quiz);
    void student(Studentmodel.Student student);

}
