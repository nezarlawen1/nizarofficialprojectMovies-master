package com.example.hp1.nizarofficialprojectmovies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageFragment extends Fragment {
    public static String BASE_URL = "https://api.themoviedb.org";
    public static int PAGE = 1;
    public static String API_KEY = "efc31ce25ae8abcffb42bd206ad17519";
    public static String LANGUAGE = "en-US";
    public static String CATEGORY = "popular";

    TextView Mname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_homepage, container, false);
        Mname = layout.findViewById(R.id.m_name);

        return layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api_Interface myInterface = retrofit.create(Api_Interface.class);

        Call<MoviesResult> call = myInterface.listofMovies(CATEGORY, API_KEY, LANGUAGE, PAGE);

        call.enqueue(new Callback<MoviesResult>() {
            @Override
            public void onResponse(Call<MoviesResult> call, Response<MoviesResult> response) {

                MoviesResult results = response.body();
                List<MoviesResult.ResultsBean> listofMovies = results.getResults();
                MoviesResult.ResultsBean firstMovie = listofMovies.get(0);
                Mname.setText(firstMovie.getTitle());
            }

            @Override
            public void onFailure(Call<MoviesResult> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

    private void setContentView(int fragment_homepage) {

    }
}