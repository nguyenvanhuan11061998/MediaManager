package com.t3h.mediamanager1.activity;

import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.ActivityStartBinding;

public class StartActivity extends BaseActivity<ActivityStartBinding> {

    String check = null;

    @Override
    protected void initAct() {
        ShareHelper shareHelper = new ShareHelper(this);
        check = shareHelper.get(ShareHelper.Keys.PASSWORD,"");
        Handler handler = new Handler();                                                                  //tạo một handler để chạy SplashActivity
        handler.postDelayed(new Runnable() {
            Intent intent;
            @Override
            public void run() {
                if (check != ""){
                    intent = new Intent(StartActivity.this,LoginActivity.class);
                }else {
                    intent = new Intent(StartActivity.this,RegisterActivity.class);
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
