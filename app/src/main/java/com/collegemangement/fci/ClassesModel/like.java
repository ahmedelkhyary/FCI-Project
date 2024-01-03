package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class like {


    @SerializedName("likesNum")
    @Expose
    private String likesNum;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(String likesNum) {
        this.likesNum = likesNum;
    }
}

