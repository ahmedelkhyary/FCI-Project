package com.collegemangement.fci;

public class ChatClass {

    String content ;
    String sender ;
    String time ;


    public ChatClass(String content, String sender, String time) {
        this.content = content;
        this.sender = sender;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
