package com.example.hp1.nizarofficialprojectmovies;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetPopularMovies {

    public static String BASE_URL = "https://api.themoviedb.org";
    public static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w";
    //public static String BASE_URL1 = "http://image.tmdb.org/t/p/";
    public static int PAGE = 1;
    public static String API_KEY = "efc31ce25ae8abcffb42bd206ad17519";
    public static String LANGUAGE = "en-US";
    public static String CATEGORY = "popular";
    private final Api_Interface api_interface;

    GetPopularMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.baseUrl(BASE_URL1)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api_interface = retrofit.create(Api_Interface.class);
    }

    void getPopularMovies (Callback<MoviesResult> moviesResultCallback) {

        Call<MoviesResult> call = api_interface.listofMovies(CATEGORY, API_KEY, LANGUAGE, PAGE);

        call.enqueue(moviesResultCallback);

    }

}
