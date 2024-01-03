package com.collegemangement.fci.ForgotPassword;


import android.util.Log;

import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.Utils;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgetPasswordPresenter {

    private HomeView view;

    public ForgetPasswordPresenter(HomeView view) {
        this.view = view;
    }

//
//    void getstudentinfo() {
//
//
//        Call<Studentmodel> mealsCall = Utils.getApi().getStudents();
//        mealsCall.enqueue(new Callback<Studentmodel>() {
//            @Override
//            public void onResponse(@NonNull Call<Studentmodel> call, @NonNull Response<Studentmodel> response) {
//                Log.e("P", "ok");
//
//                if (response.isSuccessful() && response.body() != null) {
//
//                    view.student(response.body().getInfo());
//
//                    //   Log.e("RESPONSE" , response.body().toString());
//
//                } else {
//                    // view.service("هذا الطالب موجود بالفعل");
//                    Log.e("qq", "qq");
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Studentmodel> call, Throwable t) {
//
//                view.onErrorLoading(t.getLocalizedMessage());
//
//            }
//
//
//        });
//
//
//    }
}

