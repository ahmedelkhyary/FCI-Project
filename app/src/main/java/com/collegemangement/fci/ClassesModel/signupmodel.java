package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class signupmodel {


    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("success")
    @Expose
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
