package com.t3h.mediamanager1.register;

public interface RegisterContract {
    interface Model {
    }

    interface View {
        void onAccountNotExited();
        void onAccountExited(String messager);
        void callApiFailed();

        void onRegisterNewAccountSuccess(String message);
        void onRegisterNewAccountFailed(String message);
        void onRegisterNewAccountFailed();
    }

    interface Presenter {
        void callApiCheckAccount(String username);
        void callApiRegisterNewAccount(String username, String password, String email, String phone);
    }
}
