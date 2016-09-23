package com.tabeeby.doctor.httpclient;

import android.content.Context;
import android.util.Log;

import com.tabeeby.doctor.BuildConfig;
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

    public static <S> S createService(Class<S> serviceClass, boolean isDebug, Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        String API_BASE_URL;

        if (isDebug) {
            API_BASE_URL = ServerUtils.PROD_BASE_URL;
        } else {
            API_BASE_URL = ServerUtils.TEST_BASE_URL;
        }

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}