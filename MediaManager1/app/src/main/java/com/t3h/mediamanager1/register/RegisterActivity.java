package com.t3h.mediamanager1.register;


import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.databinding.ActivityRegisterBinding;

import static com.t3h.mediamanager1.activity.StartActivity.COME_FROM_START_ACT;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {
    private RegisterFragment registerFragment;

    @Override
    protected void initAct() {
        if (registerFragment == null){
            registerFragment = RegisterFragment.getInstance();
        }

        Intent intent = getIntent();
        if (intent!=null && intent.getExtras()!=null){
            registerFragment.comeFromStartAct = intent.getExtras().getBoolean(COME_FROM_START_ACT);
        }
        showFragment(registerFragment);
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel_register_layout,fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

}
