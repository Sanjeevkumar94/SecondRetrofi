package com.example.retrofitdemo;

import android.content.Intent;
import android.icu.text.MessagePattern;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

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
    //https://jsonplaceholder.typicode.com/posts/1/comments
    Call<List<Post>> getCommentsByDynamicUrl(
            @Path("id") int userId);

    @GET("comments")
    //https://jsonplaceholder.typicode.com/comments?postId=1
    Call<List<Post>> getCommentsByQuery(
            @Query("postId") int myPostId
    );

    @GET("comments")
    //https://jsonplaceholder.typicode.com/comments?postId=1&id=2
    Call<List<Post>> getCommentsByMultipleQueries(
            @Query("postId") int myPostId,
            @Query("id") Integer myId
    );

    @GET("comments")
    Call<List<Post>> getCommentsByQueryMap(@QueryMap Map<String,Integer> parameters);

    @GET()
    Call<List<Post>> getCommentsByUrl(@Url String url);

}
