package com.collegemangement.fci.Events;
import com.collegemangement.fci.ClassesModel.getEvents;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.ClassesModel.studentsSearch;

import java.util.List;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void events(List<getEvents.Event> getEvents);


}
