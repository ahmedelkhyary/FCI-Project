package com.collegemangement.fci.Studentinfo;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.ClassesModel.studentsSearch;

import java.util.List;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void info(studentOrDocInfo.User info);


}
