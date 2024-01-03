package com.collegemangement.fci.Student;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.ClassesModel.studentsSearch;

import java.util.List;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void student(List<studentofMyLevel.Student> students);
    void studentBykey(List<studentsSearch.Student> students);


}
