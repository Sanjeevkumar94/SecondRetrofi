package com.example.retrofitdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.retrofitdemo.Networking.NetworkingHelper;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         myService = NetworkingHelper.getRetrofit().create(MyService.class);
        // createPostWithBody();
       // createPostWithField();
        createPostWithFieldMap();
    }

    private void createPostWithFieldMap() {

        Map<String,String> createPost = new HashMap<>();
        createPost.put("userId","1");
        createPost.put("title","my title");
        Call<Post> call = myService.createPostWithFieldMap(createPost);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Log.d("response<<<",""+response.body().getTitle()+" "+
                            response.body().getText()+ " "+response.body().getId());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    private void createPostWithField() {
        Call<Post> call =  myService.createPostWithField(1,"my title","my body");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Log.d("response<<<",""+response.body().getTitle()+" "+
                            response.body().getText()+ " "+response.body().getId());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    private void createPostWithBody() {
    Post post = new Post(1,"my title", "my text");
     Call<Post> call = myService.createPostWithBody(post);
     call.enqueue(new Callback<Post>() {
         @Override
         public void onResponse(Call<Post> call, Response<Post> response) {
             if(response.isSuccessful()){
                 Log.d("response<<<",""+response.body().getTitle()+""+
                         response.body().getText()+response.body().getId());
             }
         }

         @Override
         public void onFailure(Call<Post> call, Throwable t) {

         }
     });

    }
}
