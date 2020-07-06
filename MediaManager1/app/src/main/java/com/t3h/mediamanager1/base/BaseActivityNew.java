package com.t3h.mediamanager1.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivityNew extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutAct());
        ButterKnife.bind(this);
        intAct();
    }

    protected abstract int getLayoutAct();

    protected abstract void intAct();
}
