package com.collegemangement.fci.ClassesModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getdoctors {

    @SerializedName("doctors")
    @Expose
    private List<Doctor> doctors = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public class Doctor {

        @SerializedName("courses")
        @Expose
        private List<Object> courses = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Email")
        @Expose
        private String email;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("Dof")
        @Expose
        private String dof;
        @SerializedName("ProfileImagePath")
        @Expose
        private String profileImagePath;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("bio")
        @Expose
        private String bio;

        public List<Object> getCourses() {
            return courses;
        }

        public void setCourses(List<Object> courses) {
            this.courses = courses;
        }

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDof() {
            return dof;
        }

        public void setDof(String dof) {
            this.dof = dof;
        }

        public String getProfileImagePath() {
            return profileImagePath;
        }

        public void setProfileImagePath(String profileImagePath) {
            this.profileImagePath = profileImagePath;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

    }
}
