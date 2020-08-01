package com.example.retrofitdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
         myService = MyService.retrofit.create(MyService.class);
        createPost();
    }

    private void createPost() {
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
