package com.collegemangement.fci.UpdataQuestion;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void service(String message);
}
