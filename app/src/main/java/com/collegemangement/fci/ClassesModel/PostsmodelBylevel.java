package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostsmodelBylevel {


    @SerializedName("posts")
    @Expose
    private List<Post> posts = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public class Comment {

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

    }


    public class CommentUploader {

        @SerializedName("ProfileImagePath")
        @Expose
        private String ProfileImagePath;

        public String getProfileImagePath() {
            return ProfileImagePath;
        }

        public void setProfileImagePath(String profileImagePath) {
            ProfileImagePath = profileImagePath;
        }

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("Email")
        @Expose
        private String email;
        @SerializedName("Name")
        @Expose
        private String name;

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

    }


    public class Post {

        @SerializedName("comments")
        @Expose
        private List<Comment> comments = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("content")
        @Expose
        private String content;
        @SerializedName("postUploader")
        @Expose
        private PostCreator postCreator;
        @SerializedName("timestemp")
        @Expose
        private String timestemp;
        @SerializedName("sortDate")
        @Expose
        private String sortDate;
        @SerializedName("Level")
        @Expose
        private String level;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("likesNum")
        @Expose
        private String likesNum;
        @SerializedName("commentsNum")
        @Expose
        private String commentsNum;
        @SerializedName("mine")
        @Expose
        private Boolean mine;
        @SerializedName("isLiked")
        @Expose
        private Boolean isLiked;

        @SerializedName("postType")
        @Expose
        private String postType;

        @SerializedName("filePath")
        @Expose
        private String filePath;

        public String getPostType() {
            return postType;
        }

        public void setPostType(String postType) {
            this.postType = postType;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public List<Comment> getComments() {
            return comments;
        }

        public void setComments(List<Comment> comments) {
            this.comments = comments;
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

        public PostCreator getPostCreator() {
            return postCreator;
        }

        public void setPostCreator(PostCreator postCreator) {
            this.postCreator = postCreator;
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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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

        public String getCommentsNum() {
            return commentsNum;
        }

        public void setCommentsNum(String commentsNum) {
            this.commentsNum = commentsNum;
        }

        public Boolean getMine() {
            return mine;
        }

        public void setMine(Boolean mine) {
            this.mine = mine;
        }

        public Boolean getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(Boolean isLiked) {
            this.isLiked = isLiked;
        }



    }

    public class PostCreator {

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
        private String ProfileImagePath;


        public String getProfileImagePath() {
            return ProfileImagePath;
        }

        public void setProfileImagePath(String profileImagePath) {
            ProfileImagePath = profileImagePath;
        }

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

    }
}