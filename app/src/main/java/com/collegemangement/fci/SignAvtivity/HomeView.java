package com.collegemangement.fci.SignAvtivity;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void service(String result);
}
