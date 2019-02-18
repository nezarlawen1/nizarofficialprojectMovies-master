package com.example.hp1.nizarofficialprojectmovies;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userId;

    private int id;

    private String title;

    private String rating;

    private String poster_path;

    @SerializedName("body")
    private String text;

    public int getUserId() { 
        return userId;
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

    public String getRating() {
        return rating;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
