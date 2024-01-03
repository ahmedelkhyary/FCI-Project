package com.collegemangement.fci.Createquiz;


import com.collegemangement.fci.ClassesModel.createQuizByDoc;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void Result (String message , String idQuiz);
}
