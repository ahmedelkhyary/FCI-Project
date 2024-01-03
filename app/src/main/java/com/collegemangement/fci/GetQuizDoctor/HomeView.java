package com.collegemangement.fci.GetQuizDoctor;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.doctorUploadedQuizes;
import com.collegemangement.fci.ClassesModel.getMaterial;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void doctorUploadedQuizes(List<doctorUploadedQuizes.Quize> quizes);


}
