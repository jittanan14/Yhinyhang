package com.example.jittanan.yhinyhang;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("messages")
    @Expose
    private String messages;

    @SerializedName("user")
    @Expose
    private User user;

    public LoginResponse(boolean status, String messages, User user) {
        this.status = status;
        this.messages = messages;
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessages() {
        return messages;
    }

    public User getUser() {
        return user;
    }
}