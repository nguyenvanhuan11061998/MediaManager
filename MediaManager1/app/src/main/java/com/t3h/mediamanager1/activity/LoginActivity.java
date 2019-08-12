package com.t3h.mediamanager1.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener {


    @Override
    protected void initAct() {
        binding.btnLogin.setOnClickListener(this);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
//        ShareHelper helper = new ShareHelper(this);
//        String password = helper.get(ShareHelper.Keys.PASSWORD,"");
//        String pass = binding.edtPasswordLogin.getText().toString();
//
//        if (pass.equals(password)){
//            Intent intent = new Intent(this,MainActivity.class);
//            Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
//            startActivity(intent);
//            finish();
//        }else {
//            Toast.makeText(this,"Mật khẩu không đúng !",Toast.LENGTH_LONG).show();
//            return;
//        }




    }
}
