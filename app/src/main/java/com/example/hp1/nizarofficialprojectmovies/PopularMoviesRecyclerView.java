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

public class PopularMoviesRecyclerView extends RecyclerView.Adapter<PopularMoviesRecyclerView.ViewHolder> {
    private static final String TAG = "PopularMoviesRecyclerView";

    private ArrayList<String> mPicNames = new ArrayList<>();
    private ArrayList<String> mPics = new ArrayList<>();
    private Context mContext;

    public PopularMoviesRecyclerView(Context mContext, ArrayList<String> mPicNames, ArrayList<String> mPics) {
        this.mPicNames = mPicNames;
        this.mPics = mPics;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horiz_item,viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext)
                .asBitmap()
                .load(mPics.get(i))
                .into(viewHolder.moviePic);

        viewHolder.movieName.setText(mPicNames.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on : " + mPicNames.get(i));
                Toast.makeText(mContext, mPicNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPicNames.size();
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
        }
    }
}
