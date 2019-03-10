package com.example.hp1.nizarofficialprojectmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PopularMoviesRecyclerAdapter extends RecyclerView.Adapter<PopularMoviesRecyclerAdapter.ViewHolder> {
    private static final String TAG = "PopularMoviesRecyclerAdapter";
    private final List<MoviesResult.ResultsBean> movies;

    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    private Context mContext;

    public PopularMoviesRecyclerAdapter(Context mContext,
                                        List<MoviesResult.ResultsBean> listofMovies) {
        this.mContext = mContext;
        this.movies = listofMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horiz_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext)
                .asBitmap()
                .load(GetPopularMovies.BASE_URL + movies.get(i).getPoster_path())
                .into(viewHolder.moviePic);

        Glide.with(mContext)
                .asBitmap()
                .load(GetTopRated.BASE_URL + movies.get(i).getPoster_path())
                .into(viewHolder.moviePic);

        viewHolder.movieName.setText(movies.get(i).getTitle());
        //viewHolder.moviePic.setImageBitmap(movies.get(i).getPoster_path());


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePic;
        TextView movieName;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            moviePic = itemView.findViewById(R.id.moviePic);
            movieName = itemView.findViewById(R.id.movieName);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
