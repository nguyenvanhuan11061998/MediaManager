package com.t3h.mediamanager1.register.change_password;

public interface ChangePasswordContract {
    interface Model {
    }

    interface View {
        void changePasswordSuccess(String message);
        void changePasswordFailed(String message);
    }

    interface Presenter {
        void callApiChangePassword(String username, String oldPass, String newPassword);
    }
}
