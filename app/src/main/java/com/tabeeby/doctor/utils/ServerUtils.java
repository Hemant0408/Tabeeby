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
    public static final String QUESTIONLIST="question/get-questions";

    public static final String VERIFY_OTP = "verifyotp";
    public static final String RESEND_OTP = "resendotp";
    public static final String FORGOT_PASSWORD = "requestreset";
    public static final String UPDATE_PATIENT = "updatepetient";


    //status code
    public static final int STATUS_OK = 200;
}