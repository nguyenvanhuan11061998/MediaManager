package com.t3h.mediamanager1.login.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.t3h.mediamanager1.models.UserModel;

public class AccountLogin {
    @SerializedName("errorcode")
    @Expose
    private String errorcode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("account")
    @Expose
    private UserModel account;

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

    public UserModel getAccount() {
        return account;
    }

    public void setAccount(UserModel account) {
        this.account = account;
    }

}
