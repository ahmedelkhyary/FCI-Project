package com.collegemangement.fci.GetMaterial;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.getMaterial;
import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void getMaterial(List<getMaterial.Material> getMaterial);
    void student(Studentmodel.Student student);


}
