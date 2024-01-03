package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetComments {


    @SerializedName("comments")
    @Expose
    private List<Comment> comments = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public class Comment {

        @SerializedName("replayes")
        @Expose
        private List<Object> replayes = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("commentUploader")
        @Expose
        private CommentUploader commentUploader;
        @SerializedName("isLiked")
        @Expose
        private Boolean isLiked;
        @SerializedName("likesNum")
        @Expose
        private String likesNum;

        public List<Object> getReplayes() {
            return replayes;
        }

        public void setReplayes(List<Object> replayes) {
            this.replayes = replayes;
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

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public CommentUploader getCommentUploader() {
            return commentUploader;
        }

        public void setCommentUploader(CommentUploader commentUploader) {
            this.commentUploader = commentUploader;
        }

        public Boolean getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(Boolean isLiked) {
            this.isLiked = isLiked;
        }

        public String getLikesNum() {
            return likesNum;
        }

        public void setLikesNum(String likesNum) {
            this.likesNum = likesNum;
        }


        public class CommentUploader {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("Email")
            @Expose
            private String email;
            @SerializedName("Name")
            @Expose
            private String name;
            @SerializedName("level")
            @Expose
            private String level;
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

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
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