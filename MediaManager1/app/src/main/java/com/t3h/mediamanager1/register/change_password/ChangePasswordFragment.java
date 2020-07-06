package com.t3h.mediamanager1.register.change_password;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.ValidatorUtils;
import com.t3h.mediamanager1.base.BaseFragmentNew;
import com.t3h.mediamanager1.customview.UICustomButton;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordFragment extends BaseFragmentNew {
    @BindView(R.id.edt_old_password)
    EditText oldPasswordEditText;
    @BindView(R.id.edt_new_password)
    EditText newPasswordEditText;
    @BindView(R.id.edt_again_new_password)
    EditText againNewPasswordEditText;
    @BindView(R.id.btn_change_password)
    UICustomButton changePasswordButton;
    @BindView(R.id.ll_container)
    LinearLayout containerView;
    @BindView(R.id.img_back)
    ImageView backImageView;
    @BindView(R.id.tv_old_password_wrong)
    TextView oldPasswordWrongView;
    @BindView(R.id.tv_password_wrong)
    TextView passwordWrongView;
    @BindView(R.id.tv_again_password)
    TextView againPasswordView;
    @BindView(R.id.tv_password_wrong2)
    TextView passwordWrongView2;

    private boolean checkEnterOldPass = false;
    private boolean checkEnterNewPass = false;
    private boolean checkEnterAgainPass = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_change_password;
    }

    public static ChangePasswordFragment getInstance(){
        return new ChangePasswordFragment();
    }

    @Override
    protected void initFragment() {
        ValidatorUtils.setupHideKeyBroad(getView(),getActivity());
        changePasswordButton.statusDisable();

        oldPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (oldPasswordEditText.getText().length() > 0){
                    checkEnterOldPass = true;
                } else {
                    checkEnterOldPass = false;
                }
                oldPasswordWrongView.setVisibility(View.GONE);
                checkShowButtonChangePass();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        newPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (newPasswordEditText.getText().length()>0){
                    checkEnterNewPass = true;
                } else {
                    checkEnterNewPass = false;
                }
                passwordWrongView.setVisibility(View.GONE);
                passwordWrongView2.setVisibility(View.GONE);
                checkShowButtonChangePass();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        againNewPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (againNewPasswordEditText.getText().length() > 0){
                    checkEnterAgainPass = true;
                } else {
                    checkEnterAgainPass = false;
                }
                againPasswordView.setVisibility(View.GONE);
                checkShowButtonChangePass();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void checkShowButtonChangePass(){
        if (checkEnterAgainPass && checkEnterOldPass && checkEnterNewPass){
            changePasswordButton.statusActive();
        } else {
            changePasswordButton.statusDisable();
        }
    }

    @OnClick({R.id.img_back, R.id.btn_change_password})
    void onClick(View view){
        switch (view.getId()){
            case R.id.img_back:
                ChangePasswordActivity mActivity = (ChangePasswordActivity) getActivity();
                mActivity.back();
                break;
            case R.id.btn_change_password:
                changePassword();
                break;
        }
    }

    private void changePassword() {
        String oldPass = ValidatorUtils.loadModel(getContext()).getPassword();
        if (oldPass.equals(oldPasswordEditText.getText().toString())){
            oldPasswordWrongView.setVisibility(View.GONE);
        } else {
            oldPasswordWrongView.setVisibility(View.VISIBLE);
            return;
        }

        if (newPasswordEditText.getText().length() < 8){
            passwordWrongView.setVisibility(View.VISIBLE);
            return;
        } else {
            passwordWrongView.setVisibility(View.GONE);
        }

        if (oldPass.equals(newPasswordEditText.getText().toString())){
            passwordWrongView2.setVisibility(View.VISIBLE);
            return;
        } else {
            passwordWrongView2.setVisibility(View.GONE);
        }

        if (newPasswordEditText.getText().toString().equals(againNewPasswordEditText.getText().toString())){
            againPasswordView.setVisibility(View.GONE);
        } else {
            againPasswordView.setVisibility(View.VISIBLE);
            return;
        }
    }


}
