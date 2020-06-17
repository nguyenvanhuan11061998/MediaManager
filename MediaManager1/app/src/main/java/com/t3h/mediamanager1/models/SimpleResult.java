package com.t3h.mediamanager1.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimpleResult {
    @SerializedName("errorcode")
    @Expose
    private String errorcode;
    @SerializedName("message")
    @Expose
    private String message;

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
