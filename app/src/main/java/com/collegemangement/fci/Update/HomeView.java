package com.collegemangement.fci.Update;

import com.collegemangement.fci.ClassesModel.doctorUploadedQuizes;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void doctorUploadedQuizes(List<doctorUploadedQuizes.Quize> quizes);


}
