package com.zaki.retrouser.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private int userId;

    @SerializedName("name")
    private String userName;

    @SerializedName("email")
    private String userEmail;

    public User(int userId, String userName,String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail=userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

}
