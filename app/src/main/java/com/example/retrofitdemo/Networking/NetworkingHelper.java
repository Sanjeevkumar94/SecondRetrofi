package com.example.retrofitdemo.Networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkingHelper {

    static  String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public  static  Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                    .addInterceptor(new Interceptor() {
                                        @NotNull
                                        @Override
                                        public Response intercept(@NotNull Chain chain) throws IOException {
                                            Request originalRequest = chain.request();

                                            Request request = originalRequest.newBuilder()
                                                    .addHeader("Interceptor1","jskhkl")
                                                    .addHeader("Interceptor2","j9897")
                                                    .build();
                                            return chain.proceed(request);
                                        }
                                    })
                                    .addInterceptor(httpLoggingInterceptor)
                                    .build();

        Gson gson = new GsonBuilder().serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit;
    }




}
