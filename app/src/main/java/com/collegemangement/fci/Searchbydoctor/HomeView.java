package com.collegemangement.fci.Searchbydoctor;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.ClassesModel.studentsSearch;

import java.util.List;


public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void student(List<studentofMyLevel.Student> students);
    void getAllStudentbyDoctor(List<studentofMyLevel.Student> students);



}
