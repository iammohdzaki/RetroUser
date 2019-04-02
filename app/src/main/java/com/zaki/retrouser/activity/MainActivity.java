package com.zaki.retrouser.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zaki.retrouser.R;
import com.zaki.retrouser.UserDetailDialog;
import com.zaki.retrouser.adapter.UserAdapter;
import com.zaki.retrouser.api.ApiClient;
import com.zaki.retrouser.api.ApiInterface;
import com.zaki.retrouser.constant.Constant;
import com.zaki.retrouser.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<User> userArrayList = new ArrayList<>();
    private TextView tvName,tvId,tvEmail;
    private Button btnFetchPost,btnDismissDialog,btnMoreDetail;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ApiInterface apiInterface;
    private ProgressDialog progress;
    private int userPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getUsersData();


    }

    //Initialize Components
    private void init() {
        tvName = findViewById(R.id.tv_frag_user_name);
        tvId = findViewById(R.id.tv_frag_user_id);
        tvEmail = findViewById(R.id.tv_frag_user_email);

        btnFetchPost = findViewById(R.id.btn_fetch_post);
        recyclerView = findViewById(R.id.rv_user_list);
        progress = ProgressDialog.show(this, getString(R.string.progress_title), getString(R.string.progress_body), true);

        btnMoreDetail=findViewById(R.id.btn_more_details);
        btnDismissDialog=findViewById(R.id.btn_dismiss_dialog);

        userAdapter = new UserAdapter(userArrayList);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(userAdapter);
        recyclerTouchHandle();
        fetchUserPost();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);


    }

    //Fetch User Posts
    private void fetchUserPost() {
        btnFetchPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsersPostActivity.class);
                if (tvId.getText().toString().equals(Constant.USER_CURRENT_ID)) {
                    Toast.makeText(MainActivity.this, getString(R.string.no_user_click_text), Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra(Constant.USER_ID, tvId.getText().toString());
                    intent.putExtra(Constant.USER_NAME, tvName.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }


    //Get All Users List
    private void getUsersData() {

        Call<List<User>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.body().equals(null)){

                }else{
                    List<User> user = response.body();
                    for (int i = 0; i < user.size(); i++) {
                        userArrayList.add(new User(user.get(i).getUserId()
                                                 , user.get(i).getUserName()
                                                 , user.get(i).getUserEmail()
                                                 , user.get(i).getUserUniqueName()
                                                 , user.get(i).getUserPhone()
                                                 , user.get(i).getUserWebsite()));
                    }
                    userAdapter.notifyDataSetChanged();
                    progress.dismiss();
                    Toast.makeText(MainActivity.this, getString(R.string.data_fetch_successful), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progress.dismiss();
                showErrorDialog();
                Toast.makeText(MainActivity.this, getString(R.string.error_fetching_data), Toast.LENGTH_LONG).show();
            }
        });
    }

    //Recycler Touch Handling
    private void recyclerTouchHandle() {
        userAdapter.setOnClickListener(new UserAdapter.UserClickListener() {
            @Override
            public void onCLick(int position) {
                User user = userArrayList.get(position);
                setData(user.getUserName(), String.valueOf(user.getUserId()), user.getUserEmail());
                showMoreDetails(position);
                userPosition=position;

            }
        });
    }

    /**
     * Sets Data on Display Format
     *
     * @param name as User Name
     * @param id   as User Id
     */
    private void setData(String name, String id, String email) {
        tvId.setText(id);
        tvName.setText(name);
        tvEmail.setText(email);
    }

    //Error Dialog if No Internet is there
    private void showErrorDialog() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setMessage("Internet not available?");
        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getUsersData();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showMoreDetails(final int position){
        btnMoreDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                    //Toast Error
                }
                else{
                    UserDetailDialog detailDialog=new UserDetailDialog();
                    detailDialog.show(getSupportFragmentManager(),"user_profile");
                    //listener.applyTexts(userArrayList,userPosition);
                }
            }
        });
    }
}
