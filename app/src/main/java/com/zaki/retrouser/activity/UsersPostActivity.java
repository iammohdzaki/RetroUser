package com.zaki.retrouser.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zaki.retrouser.R;
import com.zaki.retrouser.adapter.UserAdapter;
import com.zaki.retrouser.adapter.UserPostAdapter;
import com.zaki.retrouser.api.ApiClient;
import com.zaki.retrouser.api.ApiInterface;
import com.zaki.retrouser.constant.Constant;
import com.zaki.retrouser.model.UserPost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersPostActivity extends AppCompatActivity {

    private int userId;
    private String userName;
    private ApiInterface apiInterface;
    private ArrayList<UserPost> userPostList=new ArrayList<>();
    private ProgressDialog progressDialog;
    private TextView tvUserName;
    private TextView tvTotalPosts;
    private int totalPostCount=0;
    private RecyclerView recyclerView;
    private UserPostAdapter userPostAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_post);

        init();
        getUserPosts();
    }

    private void init() {
        tvUserName=findViewById(R.id.tv_user_name_post_view);
        tvTotalPosts=findViewById(R.id.tv_total_post_count);
        recyclerView=findViewById(R.id.rv_user_post);

        userPostAdapter=new UserPostAdapter(userPostList);
        mLayoutManager=new LinearLayoutManager(UsersPostActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(userPostAdapter);

        Intent intent=getIntent();
        userId=Integer.parseInt(intent.getStringExtra(Constant.USER_ID));
        userName=intent.getStringExtra(Constant.USER_NAME);
        tvUserName.setText(userName);
        progressDialog=ProgressDialog.show(this,"Loading","Fetching User Post",true);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
    }


    private void getUserPosts() {
        Call<List<UserPost>> postCall=apiInterface.getUserPost(userId);
        postCall.enqueue(new Callback<List<UserPost>>() {
            @Override
            public void onResponse(Call<List<UserPost>> call, Response<List<UserPost>> response) {
                List<UserPost> userPosts=response.body();
                for(int i=0;i<userPosts.size();i++){
                    totalPostCount++;
                    userPostList.add(new UserPost(userPosts.get(i).getPostId()
                                                 ,userPosts.get(i).getPostTitle()
                                                 ,userPosts.get(i).getPostTitle()));
                }
                tvTotalPosts.setText(String.valueOf(totalPostCount));
                userPostAdapter.notifyDataSetChanged();
                Toast.makeText(UsersPostActivity.this,"Data Fetched Successfully!",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<UserPost>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

}
