package com.hiddenbean.android.khbarmdinty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hiddenbean.android.khbarmdinty.models.TextPost;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder>{

    private Context context;
    private ArrayList<TextPost> textPosts;

    public PostAdapter(Context context, ArrayList<TextPost> textPosts) {
        this.context = context;
        this.textPosts = textPosts;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        TextPost textPost = textPosts.get(position);
        holder.setDetails(textPost);
    }

    @Override
    public int getItemCount() {
        return textPosts.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        private TextView user_name, location_text, post_text;

        public PostHolder(View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_name);
            location_text = itemView.findViewById(R.id.location_text);
            post_text = itemView.findViewById(R.id.post_text);
        }

        public void setDetails(TextPost textPost) {
            user_name.setText(String.valueOf(textPost.getUser_id()));
            location_text.setText("location");
            post_text.setText(textPost.getPost());
        }
    }

}
