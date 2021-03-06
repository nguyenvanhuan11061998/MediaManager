package com.t3h.mediamanager1.api;

import com.t3h.mediamanager1.login.model.AccountLogin;
import com.t3h.mediamanager1.models.SimpleResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MediaApi {

    @FormUrlEncoded
    @POST("login.php")
    Call<AccountLogin> loginAccount(@Field("username") String username,
                                    @Field("userpassword") String password);

    @FormUrlEncoded
    @POST("checkAccountExited.php")
    Call<SimpleResult> checkAccountExited(@Field("username") String username);

    @FormUrlEncoded
    @POST("registerAccount.php")
    Call<SimpleResult> registerAccount(@Field("username") String username,
                                       @Field("password") String password,
                                       @Field("email") String email,
                                       @Field("phone") String phone);

    @FormUrlEncoded
    @POST("changePassword.php")
    Call<SimpleResult> changePassword(@Field("username") String username,
                                       @Field("old_password") String oldPassword,
                                       @Field("new_password") String newPassword);

}
