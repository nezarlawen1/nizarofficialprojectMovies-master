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

    /**
     *
     * @param mContext
     * @param listofMovies
     */
    public PopularMoviesRecyclerAdapter(Context mContext,
                                        List<MoviesResult.ResultsBean> listofMovies) {
        this.mContext = mContext;
        this.movies = listofMovies;
    }

    /**
     * This makes the content from the database view in a horiz_item way
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horiz_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * This takes the data from the database and makes it in the horiz_itm way
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");
        String imageUrl = GetPopularMovies.IMAGE_BASE_URL + "200" + movies.get(i).getPoster_path();
        Log.i(TAG, imageUrl);
        Glide.with(mContext)
                .asBitmap()
                .load(imageUrl)
                .into(viewHolder.moviePic);


        viewHolder.movieName.setText(movies.get(i).getTitle());
        //viewHolder.moviePic.setImageBitmap(movies.get(i).getPoster_path());


    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePic;
        TextView movieName;
        LinearLayout parentLayout;

        /**
         * This a listener to the movie that the user clicked on
         * @param itemView
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            moviePic = itemView.findViewById(R.id.moviePic);
            movieName = itemView.findViewById(R.id.movieName);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            moviePic.setOnClickListener(new View.OnClickListener() {
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
