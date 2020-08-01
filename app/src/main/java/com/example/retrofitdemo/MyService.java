package com.example.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyService {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    String FEED = "posts";

    Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


    @GET(FEED)
    Call<List<Post>> getPost();

    @GET("posts/1/comments")
    Call<List<Post>> getComments();

    @GET("posts/{id}/comments")
    Call<List<Post>> getCommentsByDynamicUrl(@Path("id") int userId);

}
