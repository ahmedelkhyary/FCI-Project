package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class loginmodel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setSuccess(String success) {
        this.userId = success;
    }

}