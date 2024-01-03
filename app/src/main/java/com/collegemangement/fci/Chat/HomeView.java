package com.collegemangement.fci.Chat;
import com.collegemangement.fci.ClassesModel.getMessages;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;

import java.util.List;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void info(studentOrDocInfo.User info);
    void messages(List<getMessages.Message> messages);
    void addmessage (String message);
    void readedMessges(String readedMessges);



}
