package com.example.jittanan.yhinyhang.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("messages")
    @Expose
    private String messages;

    public DefaultResponse(boolean status, String messages) {
        this.status = status;
        this.messages = messages;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessages() {
        return messages;
    }
}
