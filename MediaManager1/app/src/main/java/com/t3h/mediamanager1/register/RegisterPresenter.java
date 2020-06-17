package com.t3h.mediamanager1.register;

import com.t3h.mediamanager1.api.ApiUtils;
import com.t3h.mediamanager1.models.SimpleResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterFragment mView;

    public RegisterPresenter(RegisterFragment registerFragment) {
        this.mView = registerFragment;
    }


    @Override
    public void callApiCheckAccount(String username) {
        Call<SimpleResult> callApiCheckAccount = ApiUtils.getDataApi().checkAccountExited(username);
        callApiCheckAccount.enqueue(new Callback<SimpleResult>() {
            @Override
            public void onResponse(Call<SimpleResult> call, Response<SimpleResult> response) {
                if (response.body() != null && response.body().getErrorcode() != null){
                    if ("0".equals(response.body().getErrorcode())){
                        mView.onAccountNotExited();
                    } else {
                        mView.onAccountExited(response.body().getMessage());
                    }
                } else {
                    mView.callApiFailed();
                }
            }

            @Override
            public void onFailure(Call<SimpleResult> call, Throwable t) {
                mView.callApiFailed();
            }
        });
    }
}
