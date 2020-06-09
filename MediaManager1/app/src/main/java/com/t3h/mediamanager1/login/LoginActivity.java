package com.t3h.mediamanager1.login;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.MainActivity;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener {

    private LoginFragment loginFragment;

    @Override
    protected void initAct() {
        binding.btnLogin.setOnClickListener(this);
        if (loginFragment == null){
            loginFragment = LoginFragment.getInstance();
        }
        showFragment(loginFragment);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    public void showFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel_login_act,fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        ShareHelper helper = new ShareHelper(this);
        String password = helper.get(ShareHelper.Keys.PASSWORD,"");
        String pass = binding.edtPasswordLogin.getText().toString();

        if (pass.equals(password)){
            Intent intent = new Intent(this, MainActivity.class);
            Toast.makeText(this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this,"Mật khẩu không đúng !",Toast.LENGTH_LONG).show();
            return;
        }
    }
}
