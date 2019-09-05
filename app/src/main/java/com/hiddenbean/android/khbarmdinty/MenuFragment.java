package com.hiddenbean.android.khbarmdinty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hiddenbean.android.khbarmdinty.models.TextPost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MenuFragment extends Fragment {

    Button nextButton;
    ImageView loading_content;

    private PostAdapter adapter;
    private ArrayList<TextPost> textPostArrayList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        RecyclerView  recyclerView = view.findViewById(R.id.feed_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }
}
