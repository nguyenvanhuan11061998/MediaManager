package com.t3h.mediamanager1.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.t3h.mediamanager1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessDialog extends DialogFragment {
    @BindView(R.id.tv_description)
    TextView descriptionTextView;

    private String description = "";
    private OnClickDialogSuccess clickDialogSuccess;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.dialog_success, container, false);
        ButterKnife.bind(this, view);
        initDialog();
        return view;

    }

    private void initDialog() {
        if (description != null){
            descriptionTextView.setText(description);
        }
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        // the content
        final RelativeLayout root = new RelativeLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        // creating the fullscreen dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        return dialog;
    }

    public SuccessDialog(String description, OnClickDialogSuccess clickDialogSuccess) {
        this.clickDialogSuccess = clickDialogSuccess;
        this.description = description;
    }

    public interface OnClickDialogSuccess{
        void onClickConfirmDialog();
        void onClickCancleDialog();
    }

    @OnClick({R.id.btn_confirm, R.id.img_close_dialog})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_confirm:
                if (clickDialogSuccess != null){
                    clickDialogSuccess.onClickConfirmDialog();
                }
                break;
            case R.id.img_close_dialog:
                if (clickDialogSuccess != null){
                    clickDialogSuccess.onClickCancleDialog();
                }
                break;

        }
    }
}
