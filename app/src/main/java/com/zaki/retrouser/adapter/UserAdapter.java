package com.zaki.retrouser.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zaki.retrouser.R;
import com.zaki.retrouser.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<User> userArrayList;
    private UserClickListener userClickListener;

    public UserAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_list_format, viewGroup, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        User user=userArrayList.get(i);
        UserViewHolder holder =(UserViewHolder)viewHolder;
        holder.ivUserImage.setImageResource(R.mipmap.ic_launcher);
        holder.tvUserId.setText(String.valueOf(user.getUserId()));
        holder.tvUserName.setText(user.getUserName());
        holder.tvUserEmail.setText(user.getUserEmail());

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    private class UserViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivUserImage;
        private TextView tvUserName,tvUserId,tvUserEmail;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUserImage=itemView.findViewById(R.id.iv_user_image);
            tvUserId=itemView.findViewById(R.id.tv_user_id);
            tvUserName=itemView.findViewById(R.id.tv_user_name);
            tvUserEmail=itemView.findViewById(R.id.tv_user_email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(userClickListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            userClickListener.onCLick(position);
                        }
                    }
                }
            });
        }
    }

    public interface UserClickListener{
        void onCLick(int position);
    }

    public void setOnClickListener(UserClickListener listener){
        userClickListener=listener;
    }
}
