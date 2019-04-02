package com.zaki.retrouser.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private int userId;

    @SerializedName("name")
    private String userName;

    @SerializedName("email")
    private String userEmail;

    @SerializedName("username")
    private String userUniqueName;

    @SerializedName("phone")
    private String userPhone;

    @SerializedName("website")
    private String userWebsite;

    public User(int userId, String userName, String userEmail, String userUniqueName,String userPhone,String userWebsite) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userUniqueName=userUniqueName;
        this.userPhone=userPhone;
        this.userWebsite=userWebsite;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserWebsite() {
        return userWebsite;
    }

    public String getUserUniqueName() {
        return userUniqueName;
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
