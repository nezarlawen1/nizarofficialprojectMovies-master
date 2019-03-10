package com.example.hp1.nizarofficialprojectmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api_Interface {

    @GET("/3/movie/{category}")
    Call<MoviesResult> listofMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language")String language,
            @Query("page") int page
            //@Query("poster_path") String posterPath
    );
//    @GET("/t/w500")
//    Call<MoviesResult> listofMovies(
//            @Query("poster_path") String posterPath
//    };
}

