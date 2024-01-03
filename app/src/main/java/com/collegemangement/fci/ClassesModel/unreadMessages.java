package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class unreadMessages {

    @SerializedName("unreadMessages")
    @Expose
    private String unreadMessages;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(String unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
