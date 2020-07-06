package com.t3h.mediamanager1.register.change_password;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.ValidatorUtils;
import com.t3h.mediamanager1.base.BaseFragmentNew;

import butterknife.BindView;

public class ChangePasswordFragment extends BaseFragmentNew {
    @BindView(R.id.edt_old_password)
    EditText oldPasswordEditText;
    @BindView(R.id.edt_new_password)
    EditText newPasswordEditText;
    @BindView(R.id.edt_again_new_password)
    EditText againNewPasswordEditText;
    @BindView(R.id.btn_change_password)
    Button changePasswordButton;
    @BindView(R.id.ll_container)
    LinearLayout containerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_change_password;
    }

    @Override
    protected void initFragment() {
        ValidatorUtils.setupHideKeyBroad(getView(),getActivity());
    }
}
