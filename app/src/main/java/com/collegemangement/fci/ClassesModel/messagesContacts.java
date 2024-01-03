package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class messagesContacts {


    @SerializedName("contacts")
    @Expose
    private List<Contact> contacts = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public class Contact {


        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("ProfileImagePath")
        @Expose
        private String profileImagePath;
        @SerializedName("lastMessage")
        @Expose
        private LastMessage lastMessage;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfileImagePath() {
            return profileImagePath;
        }

        public void setProfileImagePath(String profileImagePath) {
            this.profileImagePath = profileImagePath;
        }

        public LastMessage getLastMessage() {
            return lastMessage;
        }

        public void setLastMessage(LastMessage lastMessage) {
            this.lastMessage = lastMessage;
        }


        public class LastMessage {

            @SerializedName("content")
            @Expose
            private String content;
            @SerializedName("timestemp")
            @Expose
            private String timestemp;
            @SerializedName("messageType")
            @Expose
            private String messageType;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTimestemp() {
                return timestemp;
            }

            public void setTimestemp(String timestemp) {
                this.timestemp = timestemp;
            }

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }

        }
    }
}