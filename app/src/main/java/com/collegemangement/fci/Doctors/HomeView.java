package com.collegemangement.fci.Doctors;


import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getdoctors;

import java.util.List;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void doctors(List<getdoctors.Doctor> doctors);

}
