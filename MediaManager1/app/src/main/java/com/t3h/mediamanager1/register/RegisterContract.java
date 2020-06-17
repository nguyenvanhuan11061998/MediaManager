package com.t3h.mediamanager1.register;

public interface RegisterContract {
    interface Model {
    }

    interface View {
        void onAccountNotExited();
        void onAccountExited(String messager);
        void callApiFailed();
    }

    interface Presenter {
        void callApiCheckAccount(String username);
    }
}
