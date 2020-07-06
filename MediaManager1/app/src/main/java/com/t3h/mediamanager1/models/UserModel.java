package com.t3h.mediamanager1.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("user")
    @Expose
    private String userName;
    @SerializedName("pass")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String mPhone;
    @SerializedName("phone")
    @Expose
    private String mEmail;

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getmPhone() {
        return mPhone == null ? "" : mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmEmail() {
        return mEmail == null ? "" : mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
