package com.example.retrofitdemo;

import android.content.Intent;
import android.icu.text.MessagePattern;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface MyService {
    String FEED = "posts";

    @GET(FEED)
    Call<List<Post>> getPost(@HeaderMap Map<String,String> headers);

    @Headers({"static-header:123","static-header2:1233"})
    @GET("posts/1/comments")
    Call<List<Post>> getComments( @Header("dynamic-header") String header);

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

    //https://jsonplaceholder.typicode.com/comments?postId=1&id=2
    @GET("comments")
    Call<List<Post>> getCommentsByQueryMap(@QueryMap Map<String,Integer> parameters);

    //https://jsonplaceholder.typicode.com/posts/1/comments
    @GET()
    Call<List<Post>> getCommentsByUrl(@Url String url);

    //https://jsonplaceholder.typicode.com/comments?postId=1&postId=2
    @GET("comments")
    Call<List<Post>> getCommentsByMultipleQuerieswithArrays(
            @Query("postId") Integer[] ids
    );


   /* POST https://jsonplaceholder.typicode.com/posts
            2020-08-02 13:29:11.886 1260-1324/com.example.retrofitdemo I/okhttp.OkHttpClient: Content-Type: application/json; charset=UTF-8
            2020-08-02 13:29:11.886 1260-1324/com.example.retrofitdemo I/okhttp.OkHttpClient: Content-Length: 55
            2020-08-02 13:29:11.888 1260-1324/com.example.retrofitdemo I/okhttp.OkHttpClient: {"id":0,"body":"my text","title":"my title","userId":1}
            2020-08-02 13:29:11.888 1260-1324/com.example.retrofitdemo I/okhttp.OkHttpClient: --> END POST (55-byte body)*/
    @POST("posts")
    Call<Post> createPostWithBody(@Body Post post);



   /* POST https://jsonplaceholder.typicode.com/posts
            2020-08-02 13:32:25.669 1980-2026/com.example.retrofitdemo I/okhttp.OkHttpClient: Content-Type: application/x-www-form-urlencoded
            2020-08-02 13:32:25.670 1980-2026/com.example.retrofitdemo I/okhttp.OkHttpClient: Content-Length: 40
            2020-08-02 13:32:25.671 1980-2026/com.example.retrofitdemo I/okhttp.OkHttpClient: userId=1&title=my%20title&Body=my%20body
            2020-08-02 13:32:25.671 1980-2026/com.example.retrofitdemo I/okhttp.OkHttpClient: --> END POST (40-byte body)*/
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPostWithField(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("Body") String body
    );

    /*title=my%20title&userId=1*/
    @FormUrlEncoded
    @POST("posts")
     Call<Post> createPostWithFieldMap(
             @FieldMap Map<String,String> createPost);


    @PUT("posts/{id}")
     Call<Post> putRequest( @Path("id") int id, @Body Post body);

    @PATCH("posts/{id}")
    Call<Post> patchRequest(
            @Path("id") int id,
            @Body Post body
    );

    @DELETE("posts/{id}")
    Call<Void> deleteRequest( @Path("id") int id );

}
