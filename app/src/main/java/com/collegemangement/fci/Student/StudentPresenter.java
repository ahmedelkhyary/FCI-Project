package com.collegemangement.fci.Student;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.ClassesModel.studentsSearch;
import com.collegemangement.fci.Utils;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentPresenter {

    private HomeView view;

    public StudentPresenter(HomeView view) {
        this.view = view;
    }

    void GetStudentInfo(String id){

        view.showLoading();

            view.showLoading();
            Call<studentofMyLevel> call = Utils.getApi().studentofMyLevel(id);
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
    void GetStudentInfoByKey(String key ,String idStudent){

        view.showLoading();

        view.showLoading();
        Call<studentsSearch> call = Utils.getApi().studentsSearch(key,idStudent);
        call.enqueue(new Callback<studentsSearch>() {
            @Override
            public void onResponse(@NonNull Call<studentsSearch> call, @NonNull Response<studentsSearch> response) {

                view.hideLoading();

                if (response.isSuccessful() && response.body() != null) {

                    view.studentBykey(response.body().getStudents());

                }
            }

            @Override
            public void onFailure(Call<studentsSearch> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }


        });
    }
}








