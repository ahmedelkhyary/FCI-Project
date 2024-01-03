package com.collegemangement.fci.UploadMaterial;


import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
    void result (String message);


}
