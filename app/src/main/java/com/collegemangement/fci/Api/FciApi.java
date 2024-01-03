package com.collegemangement.fci.Api;


import com.collegemangement.fci.ClassesModel.CreateComment;
import com.collegemangement.fci.ClassesModel.GetComments;
import com.collegemangement.fci.ClassesModel.Postsmodel;
import com.collegemangement.fci.ClassesModel.PostsmodelBylevel;
import com.collegemangement.fci.ClassesModel.Studentmodel;
import com.collegemangement.fci.ClassesModel.addMessage;
import com.collegemangement.fci.ClassesModel.comentReplay;
import com.collegemangement.fci.ClassesModel.commentLike;
import com.collegemangement.fci.ClassesModel.createDoc;
import com.collegemangement.fci.ClassesModel.createPost;
import com.collegemangement.fci.ClassesModel.addquestion;
import com.collegemangement.fci.ClassesModel.correctQuiz;
import com.collegemangement.fci.ClassesModel.createQuizByDoc;
import com.collegemangement.fci.ClassesModel.doctorUploadedQuizes;
import com.collegemangement.fci.ClassesModel.getEvents;
import com.collegemangement.fci.ClassesModel.getMaterial;
import com.collegemangement.fci.ClassesModel.getMessages;
import com.collegemangement.fci.ClassesModel.getQuizResult;
import com.collegemangement.fci.ClassesModel.getReplayes;
import com.collegemangement.fci.ClassesModel.getdoctors;
import com.collegemangement.fci.ClassesModel.getquiz;
import com.collegemangement.fci.ClassesModel.getquizByid;
import com.collegemangement.fci.ClassesModel.like;
import com.collegemangement.fci.ClassesModel.loginmodel;
import com.collegemangement.fci.ClassesModel.messagesContacts;
import com.collegemangement.fci.ClassesModel.postUpdate;
import com.collegemangement.fci.ClassesModel.replayLike;
import com.collegemangement.fci.ClassesModel.signupmodel;
import com.collegemangement.fci.ClassesModel.studentOrDocInfo;
import com.collegemangement.fci.ClassesModel.studentofMyLevel;
import com.collegemangement.fci.ClassesModel.studentsSearch;
import com.collegemangement.fci.ClassesModel.unreadMessages;
import com.collegemangement.fci.ClassesModel.updateProfileImage;
import com.collegemangement.fci.ClassesModel.updateQuestion;
import com.collegemangement.fci.ClassesModel.updatepassword;
import com.collegemangement.fci.ClassesModel.uploadMaterial;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface FciApi {

    // Insert new student Api

    @POST("createStudent")
    @FormUrlEncoded
    Call<signupmodel> getresultofsginup
            (
                    @Field("Name") String name,
                    @Field("email") String email,
                    @Field("Password") String password,
                    @Field("Department") String Dept,
                    @Field("Dof") String Dof,
                    @Field("level") String city,
                    @Field("Gender") String gender

            );

    // Login Api

    @POST("login")
    @FormUrlEncoded
    Call<loginmodel> getresultofsginup
            (
                    @Field("email") String email,
                    @Field("password") String password,
                    @Field("title") String title

            );


    // Get All posts' students  with same level students

    @GET("studentPosts/{studentId}")
    Call<Postsmodel> getposts(

            @Path("studentId") String studentId

    );


    // Get a student information by ID

    @GET("studentById/{studentId}")
    Call<Studentmodel> getstudentinfo(

            @Path("studentId") String studentId

    );


    //Create posts with file - photo , Pdf , word , power point

    @POST ("createPostVersion3")
    @Multipart
    Call<createPost> createPost
            (

                    @PartMap Map<String, RequestBody> map,
                    @Part("postContent") RequestBody postContent,
                    @Part("userId") RequestBody userId,
                    @Part("level") RequestBody level,
                    @Part("postType") RequestBody postType



            );

    // 8- Get all students of my same level (By Student only)

    @GET("studentofMyLevel/{studentId}")
    Call<studentofMyLevel> studentofMyLevel(

            @Path("studentId") String studentId

    );

    // Get student information by search a doctor

    @POST("studentsSearchByDoc")
    @FormUrlEncoded
    Call<studentofMyLevel> studentsSearchByDoc(

            @Field("searchKey") String searchKey,
            @Field("userId") String userId

    );

    // Get students for a doctor

    @GET("studentsByDoc/{userId}")
    Call<studentofMyLevel> studentsByDoc(

            @Path("userId") String studentId

    );


    // Get doctors for a doctor or a student

    @GET("doctorsByDoc/{userId}")
    Call<getdoctors> getdoctors(

            @Path("userId") String studentId

    );

    // Get a doctor information by search a doctor or a student

    @POST("doctorsSearch")
    @FormUrlEncoded
    Call<getdoctors> getdoctorsbyKey(

            @Field("searchKey") String searchKey,
            @Field("userId") String userId

    );


    // Make a like to posts

    @POST("postLike")
    @FormUrlEncoded
    Call<like> like(

            @Field("postId") String postId ,
            @Field("userId") String userId

    );


    // Get Comments by id of a post

    @GET("getComments/{postId}/{userId}")
    Call<GetComments> GetComments(

            @Path("postId") String postId ,
            @Path("userId") String userId

    );


    // Add comment to a post

    @POST("createComment")
    @FormUrlEncoded
    Call<CreateComment> CreateComment
            (
                    @Field("commentContent") String commentContent,
                    @Field("postId") String postId,
                    @Field("studentId") String studentId

            );


    // Like to a comment

    @POST("commentLike")
    @FormUrlEncoded
    Call<commentLike> commentLike
            (
                    @Field("commentId") String commentId,
                    @Field("studentId") String studentId

            );

    // Add replay to a comment


    @POST("comentReplay")
    @FormUrlEncoded
    Call<comentReplay> comentReplay
            (
                    @Field("commentId") String commentId,
                    @Field("studentId") String studentId,
                    @Field("replayComment") String replayComment


                    );

    // Get replays of comment

    @GET("getReplayes/{commentId}")
    Call<getReplayes> getReplayes(

            @Path("commentId") String commentId

    );

    // Add  a like to comment replay

    @POST("replayLike")
    @FormUrlEncoded
    Call<replayLike> replayLike
            (
                    @Field("replayId")  String replayId ,
                    @Field("studentId") String studentId


            );

    // Create a doctor email

    @POST("createDoc")
    @FormUrlEncoded
    Call<createDoc> createDoc
            (
                    @Field("Name")  String Name ,
                    @Field("email")  String email ,
                    @Field("city")  String city ,
                    @Field("Dof")  String Dof ,
                    @Field("title")  String gender ,  // title here refer to gender
                    @Field("Password")  String password

                    );

    // Create a quiz by doctor " Only doctor create a quiz "


    @POST("createQuizByDoc")
    @FormUrlEncoded
    Call<createQuizByDoc> createQuizByDoc
            (
                    @Field("title") String title,
                    @Field("level") String level,
                    @Field("doctorId") String doctorId

            );







    // Add questions to a quiz by doctor
    @POST("addQuetion")
    @FormUrlEncoded
    Call<addquestion> addquestion
            (

                    @Field("question") String question,
                    @Field("option1") String op1,
                    @Field("option2") String op2,
                    @Field("option3") String op3,
                    @Field("option4") String op4,
                    @Field("answer") String answer,
                    @Field("quizId") String quizId

            );

    // get student's quiz of the same level

    @GET("quizes/{studentId}")
    Call<getquiz> getQuizes(

            @Path("studentId") String studentId


    );

    // get a quiz by id

    @GET("getQuiz/{studentId}/{quizId}")
    Call<getquizByid> getquiz(

            @Path("studentId") String studentId,
            @Path("quizId") String quizId

    );


    // correct quiz

    @POST("quizResult")
    @FormUrlEncoded
    Call<correctQuiz> correctQuiz(

            @Field("studentId") String studentId,
            @Field("quizId") String quizId,
            @Field("result") String result


    );

    // Search a student

    @POST("studentsSearch/")
    @FormUrlEncoded
    Call<studentsSearch> studentsSearch(

            @Field("searchKey") String searchKey,
            @Field("studentId") String studentId

    );



    // Change a pic to a student or doctor

    @POST("updateProfileImage")
    @Multipart
    Call<updateProfileImage> updateProfileImage
            (
                    @PartMap Map<String, RequestBody> map,
                    @Part("userId") RequestBody userId

                    );

    // change Student or Doctor Password

    @POST("updatepassword")
    @FormUrlEncoded
    Call<updatepassword> updatepassword(

            @Field("oldPassword") String oldPassword,
            @Field("newPassword") String newPassword  ,
            @Field("userId") String userId,
            @Field("bio") String bio


            );


    @GET("postsByLevel/{studentId}/{level}")
    Call<PostsmodelBylevel> GetPostsByLeve(

            @Path("studentId") String studentId,
            @Path("level") String level

    );

    // Get a student or a doctor information by ID

    @GET("studentOrDocInfo/{userId}")
    Call<studentOrDocInfo> studentOrDocInfo(

            @Path("userId") String studentId

    );


    // Upload Material By Doctor

    @POST("uploadMaterialVersio2")
    @Multipart
    Call<uploadMaterial> uploadMaterial
            (
                    @PartMap Map<String, RequestBody> map,
                    @Part("userId") RequestBody userId,
                    @Part("description") RequestBody description,
                    @Part("materialType") RequestBody materialType,
                    @Part("level") RequestBody level


            );

    // get material By Level

    @GET("getMaterial/{level}")
    Call<getMaterial> getMaterial(

            @Path("level") String studentId

    );

    // 36- Get Materia Belongs To a specific Doctor (materials that Doctor upload its)

    @GET("getDoctorMaterial/{doctorId}")
    Call<getMaterial> getDoctorMaterial(

            @Path("doctorId") String doctorId

    );


    // 37- Get quizes Belongs To a specific Doctor (that doctor upload it) || or the Quizes of the Student that has been Done Before

    @GET("doctorUploadedQuizes/{doctorId}")
    Call<doctorUploadedQuizes> doctorUploadedQuizes(

            @Path("doctorId") String doctorId

    );


    //39- get Quiz Results By quiz Id (Get All reasults Of Student that taked this quiz Before)

    @GET("getQuizResult/{quizId}")
    Call<getQuizResult> getQuizResult(

            @Path("quizId") String quizId

    );

    // 40- text Post Update By post Owner (Student or Doctor)

    @POST("postUpdate")
    @FormUrlEncoded
    Call<postUpdate> postUpdate(

            @Field("userId") String userId,
            @Field("postId") String postId  ,
            @Field("postContent") String postContent

    );




// 45- Get Quiz by Doctor
    @GET("getQuizByDoc/{docId}/{quizId}")
    Call<getquizByid> getQuizByDoc
            (

                    @Path("docId") String docId,
                    @Path("quizId") String quizId

            );

    //35- update Question in Quiz By quiz owner (for Doctor only)

    @POST("updateQuestion")
    @FormUrlEncoded
    Call<updateQuestion> updateQuestion
    (

            @Field("question") String question,
            @Field("option1") String op1,
            @Field("option2") String op2,
            @Field("option3") String op3,
            @Field("option4") String op4,
            @Field("answer") String answer,
            @Field("questionId") String questionId,
            @Field("quizId") String quizId,
            @Field("doctorId") String doctorId


    );

    // 42-Get All Messages Between 2 Users

    @GET("getMessages/{currentUserId}/{secondUserId}")
    Call<getMessages> getMessages
            (

                    @Path("currentUserId") String currentUserId,
                    @Path("secondUserId") String secondUserId

            );

    // 41- Send Message from one to one user (student or doctor or admin)

    @POST("addMessage")
    @Multipart
    Call<addMessage> addMessage
            (
                    @PartMap Map<String, RequestBody> map,
                    @Part("reciverId") RequestBody reciverId,
                    @Part("senderId") RequestBody senderId,
                    @Part("message") RequestBody message,
                    @Part("messageType") RequestBody messageType


            );


    // 43-Get User Contacts (users that i have chat with them before)

    @GET("messagesContacts/{userId}")
    Call<messagesContacts> messagesContacts
            (

                    @Path("userId") String userId
            );


    // 49- user Contacts search By name

    @POST("contactsSearch")
    @FormUrlEncoded
    Call<messagesContacts> contactsSearch(

            @Field("key") String key,
            @Field("userId") String userId

    );

    // 46- Get Total Number Of Unreaded Messages of user (Student or doc or Admin)

    @GET("unreadMessages/{userId}")
    Call<unreadMessages> unreadMessages
            (

                    @Path("userId") String userId
            );

    // 48-Get number of unreaded messages between 2users

    @GET("unreadMessagesBetween2Users/{currentUser}/{secondUser}")
    Call<unreadMessages> unreadMessagesBetween2Users
            (

                    @Path("currentUser") String currentUser,
                    @Path("secondUser") String secondUser

            );

    // 47- Assign messages between 2 users as readed

    @GET("readedMessges/{currentUser}/{secondUser}")
    Call<unreadMessages> readedMessges
            (

                    @Path("currentUser") String currentUser,
                    @Path("secondUser") String secondUser

            );

    // 44-Get Events By (Doctor, student , Admin)

    @GET("getEvents")
    Call<getEvents> getEvents();



    @GET("getDoctors")
    Call<getdoctors> getDoctors();




}


