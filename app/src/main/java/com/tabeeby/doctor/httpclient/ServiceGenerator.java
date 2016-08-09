package com.tabeeby.doctor.httpclient;

import com.tabeeby.doctor.utils.ServerUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


public class ServiceGenerator {

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).connectTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES).build();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static String API_BASE_URL = ServerUtils.SERVER_URL;
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

}