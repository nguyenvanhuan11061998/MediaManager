package com.t3h.mediamanager1.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.ValidatorUtils;
import com.t3h.mediamanager1.activity.MainActivity;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentRegisterBinding;
import com.t3h.mediamanager1.models.UserModel;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> {

    @BindView(R.id.edt_user_name)
    EditText userNameEditText;
    @BindView(R.id.edt_password)
    EditText passwordEditText;
    @BindView(R.id.edt_reset_password)
    EditText resetPasswordEditText;
    @BindView(R.id.edt_your_phone)
    EditText yourPhoneEditText;
    @BindView(R.id.edt_your_email)
    EditText yourEmailEditText;
    @BindView(R.id.layout_fragment_register)
    FrameLayout fragmentRegisterLayout;
    @BindView(R.id.tv_username_wrong)
    TextView usernameWrongTextView;
    @BindView(R.id.tv_password_wrong)
    TextView passwordWrongTextView;
    @BindView(R.id.tv_again_password)
    TextView againPasswordEditText;
    @BindView(R.id.tv_your_phone)
    TextView yourPhoneTextview;
    @BindView(R.id.tv_your_email)
    TextView yourEmailTextView;
    @BindView(R.id.btn_back_register)
    ImageView backRegisterButton;

    private boolean checkUserName = false;
    private boolean checkPassword = false;
    private boolean checkAgainPass = false;
    private boolean checkYourPhone = false;
    private boolean checkYourEmail = false;

    public boolean comeFromStartAct = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    public static RegisterFragment getInstance(){
        return new RegisterFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFm();
    }

    private void initFm() {
        setupUI(fragmentRegisterLayout);
        if (comeFromStartAct){
            backRegisterButton.setVisibility(View.GONE);
        } else {
            backRegisterButton.setVisibility(View.VISIBLE);
        }

        //check username register
        userNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    // do nothing
                } else {
                    if (userNameEditText.getText().toString().length() <= 8){
                        usernameWrongTextView.setVisibility(View.VISIBLE);
                        checkUserName = false;
                    } else {
                        usernameWrongTextView.setVisibility(View.GONE);
                        checkUserName = true;
                    }
                }
            }
        });

        //check register password
        passwordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    //do nothing
                } else {
                    if (checkUserName){
                        if (passwordEditText.getText().toString().length() <= 7){
                            passwordWrongTextView.setVisibility(View.VISIBLE);
                            checkPassword = false;
                        } else {
                            passwordWrongTextView.setVisibility(View.GONE);
                            checkPassword = true;
                        }
                    }
                }
            }
        });

        //check again password
        resetPasswordEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    //do nothing
                } else {
                    if (checkPassword){
                        if (!passwordEditText.getText().toString().equals(resetPasswordEditText.getText().toString())){
                            againPasswordEditText.setVisibility(View.VISIBLE);
                            checkAgainPass = false;
                        } else {
                            againPasswordEditText.setVisibility(View.GONE);
                            checkAgainPass = true;
                        }
                    }
                }
            }
        });

        yourPhoneEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    //do nothing
                } else {
                    if (checkAgainPass){
                        if (ValidatorUtils.stringNullOrEmpty(yourPhoneEditText.getText().toString())){
                            yourPhoneTextview.setVisibility(View.VISIBLE);
                            checkYourPhone = false;
                        } else {
                            yourPhoneTextview.setVisibility(View.GONE);
                            checkYourPhone = true;
                        }
                    }
                }
            }
        });

        yourEmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    //do nothing
                } else {
                    if (checkYourPhone){
                        if (ValidatorUtils.stringNullOrEmpty(yourEmailEditText.getText().toString())){
                            yourEmailTextView.setVisibility(View.VISIBLE);
                            checkYourEmail = false;
                        } else {
                            yourEmailTextView.setVisibility(View.GONE);
                            checkYourEmail = true;
                        }
                    }
                }
            }
        });
    }

    @OnClick({R.id.btn_back_register, R.id.btn_sign_up})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back_register:
                getActivity().finish();
                break;
            case R.id.btn_sign_up:
                signUpAcount();
                break;
        }
    }

    private void signUpAcount() {
        if (!checkYourEmail){
            if (ValidatorUtils.stringNullOrEmpty(yourEmailEditText.getText().toString())){
                yourEmailTextView.setVisibility(View.VISIBLE);
                checkYourEmail = false;
            } else {
                yourEmailTextView.setVisibility(View.GONE);
                checkYourEmail = true;
            }
        }
        if (checkUserName&&checkPassword&&checkAgainPass&&checkYourPhone&&checkYourEmail) {
            // case edt trống.
            if (ValidatorUtils.stringNullOrEmpty(userNameEditText.getText().toString())) {
                Toast.makeText(getContext(), R.string.ban_chua_nhap_ten_dang_nhap, Toast.LENGTH_SHORT).show();
                return;
            } else if (ValidatorUtils.stringNullOrEmpty(passwordEditText.getText().toString())) {
                Toast.makeText(getContext(), R.string.ban_chua_nhap_mat_khau, Toast.LENGTH_SHORT).show();
                return;
            } else if (ValidatorUtils.stringNullOrEmpty(resetPasswordEditText.getText().toString())) {
                Toast.makeText(getContext(), R.string.ban_chua_nhap_mat_khau_xac_nhan, Toast.LENGTH_SHORT).show();
                return;
            } else if (ValidatorUtils.stringNullOrEmpty(yourPhoneEditText.getText().toString())) {
                Toast.makeText(getContext(), R.string.ban_chua_nhap_so_dien_thoai, Toast.LENGTH_SHORT).show();
                return;
            } else if (ValidatorUtils.stringNullOrEmpty(yourEmailEditText.getText().toString())) {
                Toast.makeText(getContext(), R.string.ban_chua_nhap_email, Toast.LENGTH_SHORT).show();
                return;
            } else {
                // case xử lý thông tin đăng ký
                UserModel userModel = new UserModel();
                userModel.setUserName(userNameEditText.getText().toString());
                userModel.setPassword(passwordEditText.getText().toString());
                userModel.setmPhone(yourPhoneEditText.getText().toString());
                userModel.setmEmail(yourEmailEditText.getText().toString());

                ValidatorUtils.setUserModel(getContext(), userModel);

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        } else {
            Toast.makeText(getContext(),R.string.vui_long_nhap_cac_thong_tin_can_thiet,Toast.LENGTH_SHORT).show();
        }
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    ValidatorUtils.hideKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
}
