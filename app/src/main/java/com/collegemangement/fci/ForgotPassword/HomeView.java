package com.collegemangement.fci.ForgotPassword;


import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.Studentmodel;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);

    void student(Studentmodel.Student student);


}
