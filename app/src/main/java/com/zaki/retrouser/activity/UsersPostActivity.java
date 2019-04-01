package com.zaki.retrouser.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
    private ArrayList<UserPost> userPostList = new ArrayList<>();
    private ProgressDialog progressDialog;
    private TextView tvUserName;
    private TextView tvTotalPosts;
    private int totalPostCount = 0;
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

    //Initialize Components
    private void init() {
        tvUserName = findViewById(R.id.tv_user_name_post_view);
        tvTotalPosts = findViewById(R.id.tv_total_post_count);
        recyclerView = findViewById(R.id.rv_user_post);

        userPostAdapter = new UserPostAdapter(userPostList);
        mLayoutManager = new LinearLayoutManager(UsersPostActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(userPostAdapter);

        Intent intent = getIntent();
        userId = Integer.parseInt(intent.getStringExtra(Constant.USER_ID));
        userName = intent.getStringExtra(Constant.USER_NAME);
        tvUserName.setText(userName);
        progressDialog = ProgressDialog.show(this, getString(R.string.loading_text), getString(R.string.post_loading_text), true);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    //Fetch User Posts of a particular id
    private void getUserPosts() {
        Call<List<UserPost>> postCall = apiInterface.getUserPost(userId);
        postCall.enqueue(new Callback<List<UserPost>>() {
            @Override
            public void onResponse(Call<List<UserPost>> call, Response<List<UserPost>> response) {
                List<UserPost> userPosts = response.body();
                for (int i = 0; i < userPosts.size(); i++) {
                    totalPostCount++;
                    userPostList.add(new UserPost(userPosts.get(i).getPostId()
                            , userPosts.get(i).getPostTitle()
                            , userPosts.get(i).getPostBody()));
                }
                tvTotalPosts.setText(String.valueOf(totalPostCount));
                userPostAdapter.notifyDataSetChanged();
                Toast.makeText(UsersPostActivity.this, getString(R.string.data_fetch_successful), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<UserPost>> call, Throwable t) {
                progressDialog.dismiss();
                showErrorDialog();
            }
        });
    }

    //Shows dialog if no internet is there
    private void showErrorDialog() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(UsersPostActivity.this);
        builder.setTitle(R.string.Internet_fetch_error);
        builder.setMessage(R.string.internet_not_available);
        builder.setPositiveButton(R.string.positive_try_again, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getUserPosts();
            }
        });
        builder.setNegativeButton(R.string.negative_back, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
