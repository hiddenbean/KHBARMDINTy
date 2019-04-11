package com.hiddenbean.android.khbarmdinty;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hiddenbean.android.khbarmdinty.models.TextPost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FeedFragment extends Fragment {

    Button nextButton;
    private PostAdapter adapter;
    private ArrayList<TextPost> textPostArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        RecyclerView  recyclerView = view.findViewById(R.id.feed_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        textPostArrayList = new ArrayList<>();
        adapter = new PostAdapter(getContext(), textPostArrayList);
        recyclerView.setAdapter(adapter);
        try {
            createListData();
        }catch (ParseException e) {
            Log.e("PARSE_EXCEPTION", e.getMessage());
        }

        return view;
    }

    private void createListData() throws ParseException {
        String dateString = "15/03/2019";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = simpleDateFormat.parse(dateString);

        TextPost textPost = new TextPost(1, "This is the post", 1, date1,  date1);
        textPostArrayList.add(textPost);
        adapter.notifyDataSetChanged();
    }
}
