package com.hiddenbean.android.khbarmdinty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hiddenbean.android.khbarmdinty.models.TextPost;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FeedFragment extends Fragment {

    Button nextButton;
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private ArrayList<TextPost> textPostArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        recyclerView = view.findViewById(R.id.feed_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        textPostArrayList = new ArrayList<>();
        adapter = new PostAdapter(getContext(), textPostArrayList);
        recyclerView.setAdapter(adapter);
        createListData();

        return view;
    }

    private void createListData() {
        TextPost textPost = new TextPost(1, "This is the post", 1, new Date("15/03/2019"),  new Date("15/03/2019"));
        textPostArrayList.add(textPost);
        adapter.notifyDataSetChanged();
    }
}
