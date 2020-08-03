package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofitdemo.Networking.NetworkingHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    MyService myWebService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //myWebService =   MyService.retrofit.create(MyService.class);

        myWebService = NetworkingHelper.getRetrofit().create(MyService.class);

       // simpleGetRequest();
       // getRequestWithoutDynamicUrl();
        getRequestWithDynamicUrl();
       // getCommentsByQueryMethod();
      // getCommentsByMultipleQueries();
        //getCommentsByQuerieyMap();
       // getCommentsByUrl();
        //getCommentsByMultipleQuerieswithArrays();

    }

    private void getCommentsByMultipleQuerieswithArrays() {
        Call<List<Post>> call = myWebService.getCommentsByMultipleQuerieswithArrays(new Integer[]{1,2});
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

    private void getCommentsByUrl() {
        Call<List<Post>> call = myWebService.getCommentsByUrl("https://jsonplaceholder.typicode.com/posts/1/comments");
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

    private void getCommentsByQuerieyMap() {
        Map<String,Integer> parms = new HashMap<>();
        parms.put("postId",1);
        parms.put("id",1);

        Call<List<Post>> call = myWebService.getCommentsByQueryMap(parms);
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

    private void getCommentsByMultipleQueries() {
        Call<List<Post>> call = myWebService.getCommentsByMultipleQueries(1,1);
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

    private void getCommentsByQueryMethod() {
        Call<List<Post>> call = myWebService.getCommentsByQuery(1);
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

    private void getRequestWithDynamicUrl() {

        Call<List<Post>> call = myWebService.getCommentsByDynamicUrl(1);
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

    private void getRequestWithoutDynamicUrl() {

        Call<List<Post>> call = myWebService.getComments("abc");
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

        Map<String,String> headers = new HashMap<>();
        headers.put("Key","56dvjksbfvjfds8dgfjdsbf");
        headers.put("token","1234567890");
        Call<List<Post>> call = myWebService.getPost(headers);
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
