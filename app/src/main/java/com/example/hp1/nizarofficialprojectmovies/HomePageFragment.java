package com.example.hp1.nizarofficialprojectmovies;

import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HomePageFragment extends Fragment {
    ArrayList<horiz_Item> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homepage,container,false);
        LinearLayout gallery = findViewById(R.id.gallery);
        LayoutInflater inflater = LayoutInflater.from(this);

        arrayList.add(new ClipData.Item(R.drawable.action, "Action"));
        arrayList.add(new ClipData.Item(R.drawable.biography, "Biorgraphy"));
        arrayList.add(new ClipData.Item(R.drawable.sports, "Sports"));
        arrayList.add(new ClipData.Item(R.drawable.history, "History"));
        arrayList.add(new ClipData.Item(R.drawable.buisness, "Buisness"));




        for(int i = 0; i< arrayList.size() ; i++){
            View view = inflater.inflate(R.layout.horiz_item, gallery, false);
            TextView text = view.findViewById(R.id.horText);
            text.setText(arrayList.get(i).getName());

            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(arrayList.get(i).getImage());

            gallery.addView(view);
        }
    }
}
