package com.collegemangement.fci.ClassesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class getquiz {

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

        @SerializedName("questions")
        @Expose
        private List<QuizUploader> questions = null;
        @SerializedName("qquizQuestions")
        @Expose
        private List<Object> qquizQuestions = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Level")
        @Expose
        private String level;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("quizUploader")
        @Expose
        private QuizUploader quizUploader;

        public List<QuizUploader> getQuestions() {
            return questions;
        }

        public void setQuestions(List<QuizUploader> questions) {
            this.questions = questions;
        }

        public List<Object> getQquizQuestions() {
            return qquizQuestions;
        }

        public void setQquizQuestions(List<Object> qquizQuestions) {
            this.qquizQuestions = qquizQuestions;
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

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
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


            public class Question {

                @SerializedName("_id")
                @Expose
                private String id;
                @SerializedName("question")
                @Expose
                private String question;
                @SerializedName("answer")
                @Expose
                private String answer;
                @SerializedName("option1")
                @Expose
                private String option1;
                @SerializedName("option2")
                @Expose
                private String option2;
                @SerializedName("option3")
                @Expose
                private String option3;
                @SerializedName("option4")
                @Expose
                private String option4;
                @SerializedName("__v")
                @Expose
                private Integer v;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getQuestion() {
                    return question;
                }

                public void setQuestion(String question) {
                    this.question = question;
                }

                public String getAnswer() {
                    return answer;
                }

                public void setAnswer(String answer) {
                    this.answer = answer;
                }

                public String getOption1() {
                    return option1;
                }

                public void setOption1(String option1) {
                    this.option1 = option1;
                }

                public String getOption2() {
                    return option2;
                }

                public void setOption2(String option2) {
                    this.option2 = option2;
                }

                public String getOption3() {
                    return option3;
                }

                public void setOption3(String option3) {
                    this.option3 = option3;
                }

                public String getOption4() {
                    return option4;
                }

                public void setOption4(String option4) {
                    this.option4 = option4;
                }

                public Integer getV() {
                    return v;
                }

                public void setV(Integer v) {
                    this.v = v;
                }



            }
        }
    }
}