package com.t3h.mediamanager1.register.change_password;

import android.widget.FrameLayout;

import androidx.fragment.app.FragmentTransaction;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivityNew;

import butterknife.BindView;

public class ChangePasswordActivity extends BaseActivityNew {
    @BindView(R.id.ll_panel_activity_change_pass)
    FrameLayout panel;

    @Override
    protected int getLayoutAct() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void intAct() {
        showFragment();
    }

    private void showFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.ll_panel_activity_change_pass, ChangePasswordFragment.getInstance());
        transaction.commitAllowingStateLoss();
    }

    public void back() {
        finish();
    }
}
