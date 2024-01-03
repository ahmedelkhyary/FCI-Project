package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Studentmodel {

    @SerializedName("student")
    @Expose
    private Student student;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public class Student {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("Email")
        @Expose
        private String email;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Department")
        @Expose
        private String department;
        @SerializedName("Dof")
        @Expose
        private String dof;
        @SerializedName("Gender")
        @Expose
        private String gender;
        @SerializedName("level")
        @Expose
        private String level;
        @SerializedName("__v")
        @Expose
        private Integer v;


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

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getDof() {
            return dof;
        }

        public void setDof(String dof) {
            this.dof = dof;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
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
    }
}