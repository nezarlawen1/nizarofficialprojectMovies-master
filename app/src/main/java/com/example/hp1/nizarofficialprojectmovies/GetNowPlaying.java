package com.example.hp1.nizarofficialprojectmovies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetNowPlaying {
    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "efc31ce25ae8abcffb42bd206ad17519";
    public static String LANGUAGE = "en-US";
    public static String CATEGORY = "now_playing";
    private final Api_Interface api_interface;

    GetNowPlaying() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api_interface = retrofit.create(Api_Interface.class);
    }

    void getNowPlaying (Callback<MoviesResult> moviesResultCallback) {

        Call<MoviesResult> call = api_interface.listofMovies(CATEGORY, API_KEY, LANGUAGE, PAGE);

        call.enqueue(moviesResultCallback);

    }
}
