package com.example.hp1.nizarofficialprojectmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api_Interface {
    /**
     *This calls the queries from The Movie DataBase so it can be called
     * @param category
     * @param apiKey
     * @param language
     * @param page
     * @return
     */
    @GET("/3/movie/{category}")
    Call<MoviesResult> listofMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language")String language,
            @Query("page") int page
            //@Query("poster_path") String posterPath
    );

}

