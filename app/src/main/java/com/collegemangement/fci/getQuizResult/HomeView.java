package com.collegemangement.fci.getQuizResult;

import com.collegemangement.fci.ClassesModel.doctorUploadedQuizes;
import com.collegemangement.fci.ClassesModel.getQuizResult;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void getQuizResult(List<getQuizResult.Result> results);


}
