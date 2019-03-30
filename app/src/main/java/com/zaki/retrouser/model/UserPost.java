package com.zaki.retrouser.model;

import com.google.gson.annotations.SerializedName;

public class UserPost {

    @SerializedName("id")
    int postId;

    @SerializedName("title")
    String postTitle;

    @SerializedName("body")
    String postBody;

    public UserPost(int postId, String postTitle, String postBody) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postBody = postBody;
    }

    public int getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

}
