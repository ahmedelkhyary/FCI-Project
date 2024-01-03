package com.collegemangement.fci.CreatedoctorEmail;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void service(String result);
}
