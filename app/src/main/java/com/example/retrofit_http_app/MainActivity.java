package com.example.retrofit_http_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private TextView tvShow;
private JsonPlaceholderApi jsonPlaceholderApi ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow=findViewById(R.id.tvShow);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       jsonPlaceholderApi =retrofit.create(JsonPlaceholderApi.class);


       // showPosts();
        showComments();

    }

    public void showPosts(){

        Call<List<Post>> call=jsonPlaceholderApi.getPosts(3);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    tvShow.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts=response.body();

                for(Post post:posts){
                    String content="" ;
                    content +="ID: "+ post.getId()+"\n";
                    content +="User ID:"+ post.getUserId()+"\n";
                    content +="Title:"+ post.getTitle()+"\n";
                    content +="Body:"+ post.getText()+"\n";
                    content +="\n\n";
                    tvShow.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvShow.append(t.getMessage());
            }
        });


    }



    public void showComments(){

        Call<List<Comment>> call=jsonPlaceholderApi.getComments("posts/{id}/comments");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if(!response.isSuccessful()){
                    tvShow.setText("Code:" + response.code());
                    return;
                }

                List<Comment> comments=response.body();

                for(Comment comment:comments){
                    String content="" ;
                    content +="Post id:" + comment.getPostId();
                    content +="ID: "+ comment.getId()+"\n";
                    content +="Name:"+ comment.getName()+"\n";
                    content +="Email:"+ comment.getEmail()+"\n";
                    content +="Body:"+ comment.getText()+"\n";
                    content +="\n\n";
                    tvShow.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                    tvShow.append(t.getMessage());
            }
        });

    }
}
