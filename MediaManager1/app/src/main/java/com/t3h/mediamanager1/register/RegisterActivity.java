package com.t3h.mediamanager1.register;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.ValidatorUtils;
import com.t3h.mediamanager1.activity.MainActivity;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> implements View.OnClickListener {
    public ShareHelper helper;

    @Override
    protected void initAct() {
        helper = new ShareHelper(this);
        binding.btnRegister.setOnClickListener(this);
        binding.btnNotRegister.setOnClickListener(this);

        helper.set(ShareHelper.Keys.PASSWORD,null);
        helper.set(ShareHelper.Keys.HINT,null);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:

                if (ValidatorUtils.isTextEmpty(binding.edtPassword,binding.edtAgainPassword,binding.edtEmail) == true){
                    Toast.makeText(this,"input is empty",Toast.LENGTH_LONG).show();
                    return;

                }else{

                    if (!binding.edtPassword.getText().toString().equals(binding.edtAgainPassword.getText().toString())){
                        Toast.makeText(this,"Xác nhận Mật khẩu không đúng",Toast.LENGTH_LONG).show();
                        return;

                    }else{

                        helper.set(ShareHelper.Keys.PASSWORD,binding.edtPassword.getText().toString());
                        helper.set(ShareHelper.Keys.HINT,binding.edtPassword.getText().toString());

                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
                break;
            case R.id.btn_not_register:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;



        }
    }
}
