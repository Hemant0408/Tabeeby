package com.tabeeby.doctor.utils;

import android.content.pm.ApplicationInfo;

/**
 * Created by Z510 on 4/26/2016.
 */
public final class ServerUtils {

    // Base Url fetch Data
    public static final String PROD_BASE_URL = "http://api.tabeeby-ry2.snsepro.com/";
    public static final String TEST_BASE_URL = "http://api.tabeeby-ry2.snsepro.com/";

    //Api
    public static final String SIGN_UP = "auth/sign-up";
    public static final String LOGIN = "auth/login";
    public static final String QUESTIONLIST = "question/get-questions";
    public static final String ADDQUESTION ="question/ask-question";
    public static final String ANSWERLIST="question/get-answers";
    public static final String ADDANSWER="question/post-answer";
    public static final String VIEWCOUNT="question/register-view";
    public static final String EVENTLIST="event/get-events";
    public static final String SAVEEVENTPIC="image-test/test.php";
    public static final String ADDEVENT="event/post-event";
    public static final String EVENTATTND="event/attend-event";
    public static final String DELETEEVENT="event/delete-event";


    public static final String TESTPIC="image-test/chunk.php";

    public static final String VERIFY_OTP = "verify-otp";
    public static final String RESEND_OTP = "resend-otp";
    public static final String FORGOT_PASSWORD = "auth/request-password-reset";
    public static final String UPDATE_PATIENT = "updatepetient";

    //status code
    public static final int STATUS_OK = 200;
}