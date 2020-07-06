package com.t3h.mediamanager1.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.models.UserModel;

public class ValidatorUtils {
    public static boolean isTextEmpty(EditText ... edts){

        boolean isTextEmpty = false;

        for (EditText edt :edts) {
            if (edt.getText().toString().isEmpty()){
                isTextEmpty = true;
                edt.setError("input is Empty");
                return isTextEmpty;
            }
        }

        return isTextEmpty;

    }

    public static boolean stringNullOrEmpty(String text){
        if (text == null || 0 == text.length() || "null".equals(text)){
            return true;
        } else {
            return false;
        }
    }

    public static UserModel loadModel(Context context){
        ShareHelper share = new ShareHelper(context);

        UserModel userModel = new UserModel();
        userModel.setUserName(share.getKeyUserModel(ShareHelper.KeysUserModel.USERNAME,""));
        userModel.setmEmail(share.getKeyUserModel(ShareHelper.KeysUserModel.EMAIL,""));
        userModel.setmPhone(share.getKeyUserModel(ShareHelper.KeysUserModel.PHONE,""));
        userModel.setPassword(share.getKeyUserModel(ShareHelper.KeysUserModel.PASSWORD,""));

        return userModel;
    }

    public static void setUserModel(Context context, UserModel userModel){
        ShareHelper share = new ShareHelper(context);

        share.setKeyUserModel(ShareHelper.KeysUserModel.USERNAME,userModel.getUserName());
        share.setKeyUserModel(ShareHelper.KeysUserModel.EMAIL,userModel.getmEmail());
        share.setKeyUserModel(ShareHelper.KeysUserModel.PHONE,userModel.getmPhone());
        share.setKeyUserModel(ShareHelper.KeysUserModel.PASSWORD,userModel.getPassword());
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        View forcusView = activity.getCurrentFocus();
        if (forcusView  != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    forcusView.getWindowToken(), 0);
        }
    }

    public static void setupUI(View view, Activity activity) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public static void setupHideKeyBroad(View view, Activity activity){
        setupUI(view, activity);
    }

}
