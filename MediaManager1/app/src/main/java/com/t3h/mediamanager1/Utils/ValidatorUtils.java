package com.t3h.mediamanager1.Utils;

import android.widget.EditText;

public class ValidatorUtils {

    public static boolean isTextEmpty(EditText ... edts){

        boolean isTextEmpty = false;

        for (EditText edt :edts) {
            if (edt.getText().toString().isEmpty()){
                isTextEmpty = true;
                edt.setError("input is Empty");
                return isTextEmpty;
            }
        }

        return isTextEmpty;

    }
}
