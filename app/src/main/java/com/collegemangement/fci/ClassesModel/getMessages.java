package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getMessages {



    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public static class Message {

        public Message(String content, String sender, String timestemp) {
            this.content = content;
            this.sender = sender;
            this.timestemp = timestemp;
        }

        @SerializedName("images")
        @Expose
        private List<String> images = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("sender")
        @Expose
        private String sender;
        @SerializedName("reciver")
        @Expose
        private String reciver;
        @SerializedName("timestemp")
        @Expose
        private String timestemp;
        @SerializedName("sortDate")
        @Expose
        private String sortDate;
        @SerializedName("messageType")
        @Expose
        private String messageType;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("sender1")
        @Expose
        private Sender1 sender1;
        @SerializedName("reciver1")
        @Expose
        private Reciver1 reciver1;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getReciver() {
            return reciver;
        }

        public void setReciver(String reciver) {
            this.reciver = reciver;
        }

        public String getTimestemp() {
            return timestemp;
        }

        public void setTimestemp(String timestemp) {
            this.timestemp = timestemp;
        }

        public String getSortDate() {
            return sortDate;
        }

        public void setSortDate(String sortDate) {
            this.sortDate = sortDate;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public Sender1 getSender1() {
            return sender1;
        }

        public void setSender1(Sender1 sender1) {
            this.sender1 = sender1;
        }

        public Reciver1 getReciver1() {
            return reciver1;
        }

        public void setReciver1(Reciver1 reciver1) {
            this.reciver1 = reciver1;
        }


        public class Reciver1 {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("Name")
            @Expose
            private String name;
            @SerializedName("ProfileImagePath")
            @Expose
            private String profileImagePath;

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
        }


            public class Sender1 {

                @SerializedName("_id")
                @Expose
                private String id;
                @SerializedName("Name")
                @Expose
                private String name;
                @SerializedName("ProfileImagePath")
                @Expose
                private String profileImagePath;

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

            }

    }
}