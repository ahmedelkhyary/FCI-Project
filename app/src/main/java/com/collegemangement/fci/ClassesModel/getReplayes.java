package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getReplayes {
    @SerializedName("replayes")
    @Expose
    private List<Replaye> replayes = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Replaye> getReplayes() {
        return replayes;
    }

    public void setReplayes(List<Replaye> replayes) {
        this.replayes = replayes;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }






    public class Replaye {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("likesNum")
        @Expose
        private String likesNum;

        @SerializedName("isLiked")
        @Expose
        private boolean isLiked;

        public boolean isLiked() {
            return isLiked;
        }

        public void setLiked(boolean liked) {
            isLiked = liked;
        }



        @SerializedName("replayUploader")
        @Expose
        private ReplayUploader replayUploader;

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

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public String getLikesNum() {
            return likesNum;
        }

        public void setLikesNum(String likesNum) {
            this.likesNum = likesNum;
        }

        public ReplayUploader getReplayUploader() {
            return replayUploader;
        }

        public void setReplayUploader(ReplayUploader replayUploader) {
            this.replayUploader = replayUploader;
        }


    public class ReplayUploader {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("Email")
        @Expose
        private String email;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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