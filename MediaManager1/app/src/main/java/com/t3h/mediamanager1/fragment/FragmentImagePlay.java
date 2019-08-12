package com.t3h.mediamanager1.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.t3h.mediamanager1.AppBinding;
import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentPlayImgBinding;

public class FragmentImagePlay extends BaseFragment<FragmentPlayImgBinding> {

    private ImageView im_play;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_play_img;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        im_play = findViewById(R.id.im_play);

        PlayModelActivity act = (PlayModelActivity) getActivity();

        AppBinding.getThumb(im_play,act.getData());
    }
}
