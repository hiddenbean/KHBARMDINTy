package com.hiddenbean.android.khbarmdinty;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.hiddenbean.android.khbarmdinty.models.TextPost;

import java.text.SimpleDateFormat;
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
    public void onBindViewHolder(@NonNull final PostHolder holder, int position) {
        TextPost textPost = textPosts.get(position);
        holder.setDetails(textPost);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_LONG)
                        .show();
            }
        });

        holder.up_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "up voted", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return textPosts.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        private TextView user_name, location_text, post_text, date_text;
        private MaterialButton up_vote;
        private ImageButton down_vote;

        public PostHolder(View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_name);
            location_text = itemView.findViewById(R.id.location_text);
            date_text = itemView.findViewById(R.id.post_date);
            post_text = itemView.findViewById(R.id.post_text);
            up_vote = itemView.findViewById(R.id.up_vote);
            down_vote = itemView.findViewById(R.id.down_vote);
        }

        public void setDetails(TextPost textPost) {
            user_name.setText(String.valueOf(textPost.getUser_id()));
            location_text.setText("location,");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM YYYY");
            date_text.setText(simpleDateFormat.format(textPost.getCreated_at()));

            post_text.setText(textPost.getPost());
        }

    }

}
