package com.example.retrofitdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutPatchDeleteActivity extends AppCompatActivity {

    MyService myService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myService = MyService.retrofit.create(MyService.class);
        //putRequest();
       // patchRequest();
        deleteRequest();
    }

    private void deleteRequest() {
        myService.deleteRequest(1).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("Delete<<",""+response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    // when we sent patch request with null value it will not update those value in data base.
    private void patchRequest() {
        myService.patchRequest(1,new Post(2,"my title",null)).enqueue(
                new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.isSuccessful()){
                            Log.d("response<<<<<"," user id "+response.body().getUserId() +
                                    " , title "+ response.body().getTitle() + " , text " +response.body()
                                    .getText() + " , id " + response.body().getId());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                    }
                }
        );

    }

    //when we use put request when send null value it store null value to data base.
    private void putRequest() {
        myService.putRequest(1,new Post(2,"my title",null)).enqueue(
                new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(response.isSuccessful()){
                            Log.d("response<<<<<"," user id "+response.body().getUserId() +
                                    " , title "+ response.body().getTitle() + " , text " +response.body()
                            .getText() + " , id " + response.body().getId());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {

                    }
                }
        );

    }
}
