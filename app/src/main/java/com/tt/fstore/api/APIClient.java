package com.tt.fstore.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tt.fstore.utils.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static ApiInterface getClient() {
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS);

        Retrofit.Builder builder = new Retrofit.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        clientBuilder = clientBuilder.addInterceptor(interceptor);

        Retrofit retrofit = builder
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))//add okHttp client
                .client(clientBuilder.build())
                .build();
        return retrofit.create(ApiInterface.class);
    }

    public static ApiInterface getClientAdmin() {
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS);

        Retrofit.Builder builder = new Retrofit.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        clientBuilder = clientBuilder.addInterceptor(interceptor);

        Retrofit retrofit = builder
                .baseUrl(Constant.ADMIN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))//add okHttp client
                .client(clientBuilder.build())
                .build();
        return retrofit.create(ApiInterface.class);
    }

}
