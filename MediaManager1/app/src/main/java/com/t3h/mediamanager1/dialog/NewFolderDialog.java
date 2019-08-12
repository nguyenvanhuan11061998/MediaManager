package com.t3h.mediamanager1.dialog;

import android.app.Dialog;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.databinding.DialogNewFolderBinding;

public class NewFolderDialog extends Dialog implements View.OnClickListener {

    private DialogNewFolderBinding binding;

    private calbackNewFolder calback;

    public void setCalback(calbackNewFolder calback) {
        this.calback = calback;
    }

    public NewFolderDialog(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_new_folder,null,false);
        setContentView(binding.getRoot());

        binding.btnDialogOK.setOnClickListener(this);
        binding.btnDialogCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_dialog_OK){
            EditText name = findViewById(R.id.edt_new_Folder);
            calback.calbackNewFolder(name.getText().toString());
            dismiss();
        }else {
            dismiss();
        }
    }

    public interface calbackNewFolder{
        void calbackNewFolder(String name);
    }
}
