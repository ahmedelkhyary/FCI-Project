package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class studentsSearch {
    @SerializedName("students")
    @Expose
    private List<Student> students = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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
        @SerializedName("Gender")
        @Expose
        private String gender;
        @SerializedName("ProfileImagePath")
        @Expose
        private String profileImagePath;

        @SerializedName("Department")
        @Expose
        private String Department;

        @SerializedName("level")
        @Expose
        private String level;

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String department) {
            Department = department;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getProfileImagePath() {
            return profileImagePath;
        }

        public void setProfileImagePath(String profileImagePath) {
            this.profileImagePath = profileImagePath;
        }

    }
}