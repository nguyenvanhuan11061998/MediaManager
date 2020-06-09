package com.t3h.mediamanager1.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;


public abstract class BaseActivity<BD extends ViewDataBinding> extends AppCompatActivity {

    protected BD binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getLayoutId());
        ButterKnife.bind(this);
        initAct();
    }

    protected abstract void initAct();

    protected abstract int getLayoutId();
}
