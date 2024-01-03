package com.collegemangement.fci.Searchbydoctor;

import androidx.annotation.NonNull;

import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.ClassesModel.studentsSearch;
import com.collegemangement.fci.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchDoctorPresenter {

    private HomeView view;

    public SearchDoctorPresenter(HomeView view) {
        this.view = view;
    }

    void GetStudentInfo(String key , String id){

        view.showLoading();

            view.showLoading();
            Call<studentofMyLevel> call = Utils.getApi().studentsSearchByDoc(key, id);
            call.enqueue(new Callback<studentofMyLevel>() {
                @Override
                public void onResponse(@NonNull Call<studentofMyLevel> call, @NonNull Response<studentofMyLevel> response) {

                    view.hideLoading();

                    if (response.isSuccessful() && response.body() != null) {

                        view.student(response.body().getStudents());

                    }
                }

                @Override
                public void onFailure(Call<studentofMyLevel> call, Throwable t) {
                    view.hideLoading();
                    view.onErrorLoading(t.getLocalizedMessage());

                }


            });
        }


    void GetStudentInfo(String id){

        view.showLoading();

        view.showLoading();
        Call<studentofMyLevel> call = Utils.getApi().studentsByDoc(id);
        call.enqueue(new Callback<studentofMyLevel>() {
            @Override
            public void onResponse(@NonNull Call<studentofMyLevel> call, @NonNull Response<studentofMyLevel> response) {

                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.getAllStudentbyDoctor(response.body().getStudents());

                }
            }

            @Override
            public void onFailure(Call<studentofMyLevel> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }

}








