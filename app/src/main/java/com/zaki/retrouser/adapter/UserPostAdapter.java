package com.zaki.retrouser.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zaki.retrouser.R;
import com.zaki.retrouser.model.UserPost;


import java.util.ArrayList;

public class UserPostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<UserPost> userPostArrayList;

    public UserPostAdapter(ArrayList<UserPost> userPostArrayList) {
        this.userPostArrayList = userPostArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_post_format,viewGroup,false);
        return new UserPostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UserPost posts=userPostArrayList.get(i);
        UserPostViewHolder holder=(UserPostViewHolder) viewHolder;
        holder.tvPostId.setText(String.valueOf(posts.getPostId()));
        holder.tvPostTitle.setText(posts.getPostTitle());
        holder.tvPostBody.setText(posts.getPostBody());
    }

    @Override
    public int getItemCount() {
        return userPostArrayList.size();
    }

    private class UserPostViewHolder extends RecyclerView.ViewHolder{
        private TextView tvPostTitle,tvPostBody,tvPostId;

        public UserPostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPostId=itemView.findViewById(R.id.tv_post_id);
            tvPostTitle=itemView.findViewById(R.id.tv_post_title);
            tvPostBody=itemView.findViewById(R.id.tv_post_body);
        }
    }
}
