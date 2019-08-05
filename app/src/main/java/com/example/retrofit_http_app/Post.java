package com.example.retrofit_http_app;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int UserId,id;
    private String title;

    @SerializedName("body")
    private  String text;

    public int getUserId() {
        return UserId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
