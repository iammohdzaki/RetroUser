package com.zaki.retrouser.api;

import com.zaki.retrouser.model.User;
import com.zaki.retrouser.model.UserPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/users")
    Call<List<User>> getUsers();

    @GET("/posts")
    Call<List<UserPost>> getUserPost(@Query("userId") int id);
}
