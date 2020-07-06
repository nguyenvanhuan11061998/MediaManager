package com.t3h.mediamanager1.activity;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.ValidatorUtils;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.ActivityStartBinding;
import com.t3h.mediamanager1.login.LoginActivity;
import com.t3h.mediamanager1.models.UserModel;
import com.t3h.mediamanager1.register.RegisterActivity;

public class StartActivity extends BaseActivity<ActivityStartBinding> {
    public static final String COME_FROM_START_ACT = "false";

    boolean check = false;

    @Override
    protected void initAct() {
        UserModel userModel = ValidatorUtils.loadModel(this);
        if (null != userModel.getUserName() && "" != userModel.getUserName()){
            check = true;
        } else {
            check = false;
        }
        Handler handler = new Handler();                                                                  //tạo một handler để chạy SplashActivity
        handler.postDelayed(new Runnable() {
            Intent intent;
            @Override
            public void run() {
                if (check){
                    intent = new Intent(StartActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(StartActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },2000);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }
}
