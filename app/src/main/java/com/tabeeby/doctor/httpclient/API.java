package com.tabeeby.doctor.httpclient;

import com.tabeeby.doctor.utils.ServerUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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

    @GET(ServerUtils.QUESTIONLIST)
    Call<ResponseBody> QuestionListApi();

}