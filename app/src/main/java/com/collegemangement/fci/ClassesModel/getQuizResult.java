package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getQuizResult {
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("quiz")
    @Expose
    private Result.Student.Quiz quiz;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Result.Student.Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Result.Student.Quiz quiz) {
        this.quiz = quiz;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public class Result {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("Result")
        @Expose
        private Integer result;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("student")
        @Expose
        private Student student;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getResult() {
            return result;
        }

        public void setResult(Integer result) {
            this.result = result;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
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

            public String getProfileImagePath() {
                return profileImagePath;
            }

            public void setProfileImagePath(String profileImagePath) {
                this.profileImagePath = profileImagePath;
            }


            public class Quiz {

                @SerializedName("_id")
                @Expose
                private String id;
                @SerializedName("Title")
                @Expose
                private String title;
                @SerializedName("QuestionNum")
                @Expose
                private String questionNum;
                @SerializedName("quizUploader")
                @Expose
                private QuizUploader quizUploader;

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
    }

}
