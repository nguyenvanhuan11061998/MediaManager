package com.t3h.mediamanager1.login;
import com.t3h.mediamanager1.api.ApiUtils;
import com.t3h.mediamanager1.login.model.AccountLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginFragment mView;

    public LoginPresenter(LoginFragment mView) {
        this.mView = mView;
    }

    @Override
    public void callApiLogin(String username, String password) {
        Call<AccountLogin> callApiLogin = ApiUtils.getDataApi().loginAccount(username,password);
        callApiLogin.enqueue(new Callback<AccountLogin>() {
            @Override
            public void onResponse(Call<AccountLogin> call, Response<AccountLogin> response) {
                if ("0".equals(response.body().getErrorcode())){
                    mView.callApiLoginSuccess(response.body().getAccount());
                } else {
                    mView.callApiLoginFailed(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<AccountLogin> call, Throwable t) {
                mView.callApiLoginFailed();
            }
        });
    }
}
