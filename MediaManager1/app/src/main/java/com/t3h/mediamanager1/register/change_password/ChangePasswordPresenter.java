package com.t3h.mediamanager1.register.change_password;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.api.ApiUtils;
import com.t3h.mediamanager1.models.SimpleResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter {
    private ChangePasswordFragment mView;

    public ChangePasswordPresenter(ChangePasswordFragment fragment) {
        mView = fragment;
    }

    @Override
    public void callApiChangePassword(String username, String oldPass, String newPassword) {
        Call<SimpleResult> callback = ApiUtils.getDataApi().changePassword(username, oldPass, newPassword);
        callback.enqueue(new Callback<SimpleResult>() {
            @Override
            public void onResponse(Call<SimpleResult> call, Response<SimpleResult> response) {
                if (response.body() != null && response.body().getErrorcode() != null && response.body().getMessage() != null) {
                    if ("0".equals(response.body().getErrorcode())){
                        mView.changePasswordSuccess(response.body().getMessage());
                    } else {
                        mView.changePasswordFailed(response.body().getMessage());
                    }
                } else {
                    mView.changePasswordFailed(mView.getString(R.string.he_thong_ban_vui_long_thu_lai_sau));
                }

            }

            @Override
            public void onFailure(Call<SimpleResult> call, Throwable t) {
                mView.changePasswordFailed(mView.getString(R.string.he_thong_ban_vui_long_thu_lai_sau));
            }
        });
    }
}
