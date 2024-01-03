package com.collegemangement.fci.AddQuestiion;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void service(String message);
}
