package com.collegemangement.fci.LoginApiActivity;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void service(String result  , String id );
}
