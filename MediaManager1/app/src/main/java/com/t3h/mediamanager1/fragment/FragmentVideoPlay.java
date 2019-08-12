package com.t3h.mediamanager1.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.MediaController;
import android.widget.VideoView;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentPlayVideoBinding;

public class FragmentVideoPlay extends BaseFragment<FragmentPlayVideoBinding> {

    private VideoView vvPlayVideo;
    private MediaController controller;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_play_video;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PlayModelActivity act = (PlayModelActivity) getActivity();

        vvPlayVideo = findViewById(R.id.myVideo);
        vvPlayVideo.setVideoPath(act.getData());

        if (controller == null){
            controller = new MediaController(getContext());
        }
        controller.setAnchorView(vvPlayVideo);                                                      //cố định cotroller với video
        vvPlayVideo.setMediaController(controller);                                                 //cài bộ điều khiển video cho video

        vvPlayVideo.start();

    }
}
