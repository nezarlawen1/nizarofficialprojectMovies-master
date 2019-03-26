package com.example.hp1.nizarofficialprojectmovies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * queries from the Api_interface that can take content from he database
 */
public class GetUpComing {

    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "efc31ce25ae8abcffb42bd206ad17519";
    public static String LANGUAGE = "en-US";
    public static String CATEGORY = "upcoming";
    private final Api_Interface api_interface;

    GetUpComing(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api_interface = retrofit.create(Api_Interface.class);
    }
    /**
     * call back this class in it's fragment class
     * @param moviesResultCallback
     */
    void getupComing (Callback<MoviesResult> moviesResultCallback) {

        Call<MoviesResult> call = api_interface.listofMovies(CATEGORY, API_KEY, LANGUAGE, PAGE);

        call.enqueue(moviesResultCallback);

    }
}
