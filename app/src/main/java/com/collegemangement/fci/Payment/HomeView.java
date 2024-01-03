package com.collegemangement.fci.Payment;


import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;

import java.util.List;


public interface HomeView {
    void showLoading();

    void hideLoading();

    void onErrorLoading(String message);

    void info (studentOrDocInfo.User studentOrDocInfo);

}
