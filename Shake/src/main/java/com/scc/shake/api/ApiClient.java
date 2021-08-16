package com.scc.shake.api;


import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://192.168.0.111/best/Feedback-Phoebe/public/api/";

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();


    private static final Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()));


    private static Retrofit retrofit;
    private static ApiService API_SERVICE_INSTANCE;

    private static Retrofit getRetrofit() {
        if (retrofit == null) retrofit = builder.build();
        return retrofit;
    }

    public static ApiService getApiService() {
        if (API_SERVICE_INSTANCE == null)
            API_SERVICE_INSTANCE = getRetrofit().create(ApiService.class);
        return API_SERVICE_INSTANCE;
    }


    public static void cancelAllRequest() {
        if (okHttpClient != null)
            okHttpClient.dispatcher().cancelAll();
    }
}