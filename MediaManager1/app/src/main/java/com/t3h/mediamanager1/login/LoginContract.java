package com.t3h.mediamanager1.login;

import com.t3h.mediamanager1.models.UserModel;

public interface LoginContract {
    interface Model {

    }

    interface View {
        void callApiLoginSuccess(UserModel userModel);
        void callApiLoginFailed(String message);
        void callApiLoginFailed();
    }

    interface Presenter {
        void callApiLogin(String username, String password);
    }
}
