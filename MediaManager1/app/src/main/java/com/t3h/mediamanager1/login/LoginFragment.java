package com.t3h.mediamanager1.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.Utils.AsteriskPasswordTransformationMethod;
import com.t3h.mediamanager1.Utils.ValidatorUtils;
import com.t3h.mediamanager1.activity.MainActivity;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.dao.ShareHelper;
import com.t3h.mediamanager1.databinding.FragmentLoginBinding;
import com.t3h.mediamanager1.models.UserModel;
import com.t3h.mediamanager1.register.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {

    @BindView(R.id.edt_user_name)
    EditText userNameEditText;
    @BindView(R.id.edt_password)
    EditText passwordEditText;
    @BindView(R.id.layout_fragment_login)
    FrameLayout fragmentLoginLayout;
    @BindView(R.id.img_show_password)
    ImageView showPasswordImageView;

    private boolean isShowPass = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    public static LoginFragment getInstance(){
        return new LoginFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intFm();
    }

    private void intFm() {
        setupUI(fragmentLoginLayout);
    }

    @OnClick({R.id.tv_sign_up, R.id.btn_login, R.id.img_show_password})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_sign_up:
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.btn_login:
                login();
                break;

            case R.id.img_show_password:
                isShowPass = !isShowPass;
                if (isShowPass){
                    showPasswordImageView.setImageResource(R.drawable.ic_hide_password_login);
                    passwordEditText.setTransformationMethod(null);
                } else {
                    showPasswordImageView.setImageResource(R.drawable.ic_show_password_login);
                    passwordEditText.setTransformationMethod(new AsteriskPasswordTransformationMethod());
                }
                break;
        }

    }

    private void login() {
        if (ValidatorUtils.stringNullOrEmpty(userNameEditText.getText().toString())){
            Toast.makeText(getContext(), R.string.ban_chua_nhap_ten_dang_nhap,Toast.LENGTH_SHORT).show();
            return;
        } else if (ValidatorUtils.stringNullOrEmpty(passwordEditText.getText().toString())){
            Toast.makeText(getContext(), R.string.ban_chua_nhap_mat_khau,Toast.LENGTH_SHORT).show();
            return;
        } else {
            UserModel userModel = ValidatorUtils.loadModel(getContext());
            if ((userNameEditText.getText().toString().equals(userModel.getUserName()))
                    && passwordEditText.getText().toString().equals(userModel.getPassword())) {
                Toast.makeText(getContext(), R.string.dang_nhap_thanh_cong, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            } else {
                Toast.makeText(getContext(),R.string.ban_da_nhap_sai_ten_dang_nhap_hoac_mat_khau, Toast.LENGTH_SHORT).show();
                return;
            }
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
