package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyService myWebService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebService =   MyService.retrofit.create(MyService.class);
       //simpleGetRequest();
        getRequestWithoutDynamicUrl();

       
    }

    private void getRequestWithoutDynamicUrl() {

        Call<List<Post>> call = myWebService.getComments();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    for( Post item : response.body() ){
                        Log.d("data<<<<<<",""+item.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            Log.d("failure",t.getMessage());
            }
        });
    }

    private void simpleGetRequest() {
        Call<List<Post>> call = myWebService.getPost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    for( Post item : response.body() ){
                        Log.d("data<<<<<<",""+response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

  
}
