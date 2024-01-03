package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class doctorUploadedQuizes {

    @SerializedName("quizes")
    @Expose
    private List<Quize> quizes = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Quize> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quize> quizes) {
        this.quizes = quizes;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public class Quize {

        @SerializedName("StudentTakedExam")
        @Expose
        private List<Object> studentTakedExam = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Level")
        @Expose
        private String level;
        @SerializedName("isPuplished")
        @Expose
        private Boolean isPuplished;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("QuestionNum")
        @Expose
        private String questionNum;
        @SerializedName("quizUploader")
        @Expose
        private QuizUploader quizUploader;

        public List<Object> getStudentTakedExam() {
            return studentTakedExam;
        }

        public void setStudentTakedExam(List<Object> studentTakedExam) {
            this.studentTakedExam = studentTakedExam;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public Boolean getIsPuplished() {
            return isPuplished;
        }

        public void setIsPuplished(Boolean isPuplished) {
            this.isPuplished = isPuplished;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public String getQuestionNum() {
            return questionNum;
        }

        public void setQuestionNum(String questionNum) {
            this.questionNum = questionNum;
        }

        public QuizUploader getQuizUploader() {
            return quizUploader;
        }

        public void setQuizUploader(QuizUploader quizUploader) {
            this.quizUploader = quizUploader;
        }


        public class QuizUploader {

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

            public String getProfileImagePath() {
                return profileImagePath;
            }

            public void setProfileImagePath(String profileImagePath) {
                this.profileImagePath = profileImagePath;
            }


        }

    }
}
