package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class createQuizByDoc {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("newQuiz")
    @Expose
    private NewQuiz newQuiz;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NewQuiz getNewQuiz() {
        return newQuiz;
    }

    public void setNewQuiz(NewQuiz newQuiz) {
        this.newQuiz = newQuiz;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }



public class NewQuiz {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Level")
    @Expose
    private String level;
    @SerializedName("_id")
    @Expose
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}}