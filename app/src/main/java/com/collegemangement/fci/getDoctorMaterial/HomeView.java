package com.collegemangement.fci.getDoctorMaterial;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getMaterial;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void getMaterial(List<getMaterial.Material> getMaterial);


}
