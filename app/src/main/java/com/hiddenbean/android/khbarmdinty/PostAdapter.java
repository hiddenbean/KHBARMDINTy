package com.hiddenbean.android.khbarmdinty;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
    }

    @Override
    public int getItemCount() {
        return textPosts.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        private TextView user_name, location_text, post_text, date_text;
        private MaterialButton up_vote;
        private ImageButton down_vote, optionsButton;
        private ImageView imageView;
        private boolean isUpVoted = false;
        private boolean isDownVoted = false;

        public PostHolder(View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_name);
            location_text = itemView.findViewById(R.id.location_text);
            date_text = itemView.findViewById(R.id.post_date);
            post_text = itemView.findViewById(R.id.post_text);
            up_vote = itemView.findViewById(R.id.up_vote);
            down_vote = itemView.findViewById(R.id.down_vote);
            imageView = itemView.findViewById(R.id.post_media);
            optionsButton = itemView.findViewById(R.id.options_button);
        }

        public void setDetails(TextPost textPost) {
            user_name.setText("Hicham Msaaf");
            location_text.setText("location,");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM YYYY");
            date_text.setText(simpleDateFormat.format(textPost.getCreated_at()));

            post_text.setText(Html.fromHtml(textPost.getPost()));

            if(!textPost.isMediaPost()) {
                imageView.setVisibility(View.GONE);
                post_text.setTextSize(26);
            }
            optionsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showMenu(v);
                }
            });


            up_vote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isUpVoted) {
                        up_vote.setTextColor(context.getColor(R.color.colorPrimary));
                        up_vote.setIconTint(context.getColorStateList(R.color.colorPrimary));
                        isUpVoted = true;
                        down_vote.setImageTintList(context.getColorStateList(R.color.placeholderText));
                        isDownVoted = false;
                    }
                    else {
                        up_vote.setTextColor(context.getColor(R.color.placeholderText));
                        up_vote.setIconTint(context.getColorStateList(R.color.placeholderText));
                        isUpVoted = false;
                    }
                }
            });

            down_vote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isDownVoted) {
                        down_vote.setImageTintList(context.getColorStateList(R.color.colorPrimary));
                        isDownVoted = true;
                        up_vote.setTextColor(context.getColor(R.color.placeholderText));
                        up_vote.setIconTint(context.getColorStateList(R.color.placeholderText));
                        isUpVoted = false;
                    }
                    else {
                        down_vote.setImageTintList(context.getColorStateList(R.color.placeholderText));
                        isDownVoted = false;
                    }
                }
            });
        }

        public void showMenu(View anchor) {
            PopupMenu popup = new PopupMenu(context , anchor);
            popup.getMenuInflater().inflate(R.menu.options_menu, popup.getMenu());
            popup.show();
        }

    }

}
