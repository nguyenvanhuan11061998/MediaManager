package com.t3h.mediamanager1.login;

import android.widget.TextView;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentLoginBinding;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    public static LoginFragment getInstance(){
        return new LoginFragment();
    }

    @OnClick(R.id.tv_sign_up)
    void onClick(){

    }
}
