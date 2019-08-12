package com.t3h.mediamanager1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.DriveActivity;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentStartBinding;
import com.t3h.mediamanager1.interfaceFragment.ClickFmStart;

public class FragmentStart extends BaseFragment<FragmentStartBinding> implements ClickFmStart {




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_start;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setListener(this);
    }

    @Override
    public void onClickFmImage() {
        getParent().showFragment(getParent().getFmImage());
    }

    @Override
    public void onClickFmMusic() {
        getParent().showFragment(getParent().getFmMusic());
    }

    @Override
    public void onClickFmVideo() {
        getParent().showFragment(getParent().getFmVideo());
    }

    @Override
    public void onFmYourImg() {
        Intent intent = new Intent(getContext(),PlayModelActivity.class);
        intent.putExtra(getParent().EXTRA_INDEX_FM,3);
        startActivity(intent);
    }

    @Override
    public void onFmYourVideo() {
        Intent intent = new Intent(getContext(),PlayModelActivity.class);
        intent.putExtra(getParent().EXTRA_INDEX_FM,4);
        startActivity(intent);
    }

    @Override
    public void onBackup() {
        Intent intent = new Intent(getContext(), DriveActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDownload() {

    }


}
