package com.t3h.mediamanager1.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.t3h.mediamanager1.R;

public class UICustomButton extends AppCompatButton {
    private int buttonTypeAttributeSet = 1;
    private StateListDrawable mNormalDrawable;

    public UICustomButton(Context context) {
        super(context);
        init(context,null);
    }

    public UICustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public UICustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        mNormalDrawable = new StateListDrawable();

        if (attributeSet != null) {
            TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.UIButtonCustom);
            buttonTypeAttributeSet = a.getInteger(R.styleable.UIButtonCustom_set_button_type, 1);
            if (buttonTypeAttributeSet == 1) {
                statusActive();
            } else if (buttonTypeAttributeSet == 2) {
                statusDisable();
            }
            a.recycle();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setStateListAnimator(null);
        }

    }

    public void statusDisable() {
        this.setEnabled(false);
        this.setTextColor(Color.WHITE);
        setBackgroundCompat(mNormalDrawable);
        this.setBackgroundResource(R.drawable.custom_round_corner_button_disable_v2);
    }

    public void statusActive() {
        this.setEnabled(true);
        this.setTextColor(Color.WHITE);
        setBackgroundCompat(mNormalDrawable);
        this.setBackgroundResource(R.drawable.custom_round_corner_button_v2);
    }

    /**
     * Set the View's background. Masks the API changes made in Jelly Bean.
     * Đặt nền của Chế độ xem.
     *
     * @param drawable
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public void setBackgroundCompat(Drawable drawable) {
        int pL = getPaddingLeft();
        int pT = getPaddingTop();
        int pR = getPaddingRight();
        int pB = getPaddingBottom();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
        setPadding(pL, pT, pR, pB);
    }
}
