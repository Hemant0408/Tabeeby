package com.tabeeby.doctor.httpclient;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.tabeeby.doctor.utils.ServerUtils;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface API {

    //Signup api for doctor
    @FormUrlEncoded
    @POST(ServerUtils.SIGN_UP)
    Call<ResponseBody> signupApi(     @Field("email") String email
                                    , @Field("password") String password
                                    , @Field("confirm_password") String confirm_password
                                    , @Field("first_name") String first_name
                                    , @Field("last_name") String last_name
                                    , @Field("full_name") String full_name
                                    , @Field("user_type") String user_type
                                    , @Field("login_type") String login_type
                                    , @Field("terms") String terms
                                    , @Field("mobile_no") String mobile_no
                                    , @Field("country") String country
                                    , @Field("title") String title
                                    , @Field("lang") String lang);


    // Login api call for doctor/patient/professional
    @POST(ServerUtils.LOGIN)
    @FormUrlEncoded
    Call<ResponseBody> loginApi(  @Field("username") String email_phone
                                , @Field("password") String password);


    @FormUrlEncoded
    @POST(ServerUtils.ADDQUESTION)
    Call<ResponseBody> addQuestionApi( @Header("Authorization") String Authorization
                                     , @Field("title") String title
                                     , @Field("cat_id") String categorydesc
                                     , @Field("question_text") String question_text
                                     , @Field("status") String status
                                     , @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST(ServerUtils.ADDANSWER)
    Call<ResponseBody> addAnswerApi(  @Header("Authorization") String Authorization
                                    , @Field("question_id") String question_id
                                    , @Field("user_id") String user_id
                                    , @Field("status") String status
                                    , @Field("answer_text") String answer_text
                                    );

    @FormUrlEncoded
    @POST(ServerUtils.VIEWCOUNT)
    Call<ResponseBody> addViewCountApi(   @Header("Authorization") String Authorization
                                        , @Field("question_id") String question_id
                                        , @Field("user_id") String user_id);


    @GET(ServerUtils.QUESTIONLIST)
    Call<ResponseBody> QuestionListApi();

    @GET(ServerUtils.ANSWERLIST)
    Call<ResponseBody> AnswerListApi(@Query("question_id") String question_id);


    @GET(ServerUtils.EVENTLIST)
    Call<ResponseBody> EventListApi();

    @FormUrlEncoded
    @POST(ServerUtils.SAVEEVENTPIC)
    Call<ResponseBody> imageUpload(  @Field("base64") String base64
                                        ,@Field("ImageName") String ImageName);


    @Multipart
    @POST(ServerUtils.TESTPIC)
    Call<ResponseBody> upload(@Part("filename") RequestBody filename,
                              @Part("uploadfile") MultipartBody.Part file);


}