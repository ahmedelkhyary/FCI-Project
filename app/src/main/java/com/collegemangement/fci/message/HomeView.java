package com.collegemangement.fci.message;
import android.widget.TextView;

import com.collegemangement.fci.ClassesModel.messagesContacts;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.ClassesModel.studentsSearch;

import java.util.List;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void messagesContacts(List<messagesContacts.Contact> messagesContacts);
    void SearchBykey(List<messagesContacts.Contact> SearchBykey);
    void unreadMessagesBetween2Users(String unreadMessagesBetween2Users , TextView textView);


}
