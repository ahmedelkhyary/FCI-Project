package com.collegemangement.fci.Updata;

import com.collegemangement.fci.ClassesModel.getQuizResult;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void Result (String results);


}
