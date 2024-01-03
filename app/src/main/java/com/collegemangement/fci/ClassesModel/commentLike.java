package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class commentLike {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("likesNum")
    @Expose
    private String likesNum;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(String likesNum) {
        this.likesNum = likesNum;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


}
