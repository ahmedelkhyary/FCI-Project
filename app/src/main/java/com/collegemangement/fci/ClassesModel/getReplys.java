package com.collegemangement.fci.ClassesModel;

public class getReplys {


    private String id;
    private String content;
    private Integer likeNum;
    private Boolean isLiked;




    private String profileImage;
    private String fullName;

    public getReplys(String id, String content, Integer likeNum, Boolean isLiked, String fullName, String profileImage) {
        this.id = id;
        this.content = content;
        this.likeNum = likeNum;
        this.isLiked = isLiked;
        this.profileImage = profileImage;
        this.fullName = fullName;
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

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
