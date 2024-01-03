package com.collegemangement.fci.Classmodeloflist;

import java.util.List;

public class modeloflist {
    String name ;
    String grade ;
    String date ;
    String photo ;
    String text ;
    String id ;
    List <String> likes ;
    List <String> files ;

    public modeloflist(String name, String grade, String date, String photo, String text , String id , List<String> likes , List<String> files) {
        this.name = name;
        this.grade = grade;
        this.date = date;
        this.photo = photo;
        this.text = text;
        this.id = id ;
        this.likes = likes ;
        this.files = files ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
