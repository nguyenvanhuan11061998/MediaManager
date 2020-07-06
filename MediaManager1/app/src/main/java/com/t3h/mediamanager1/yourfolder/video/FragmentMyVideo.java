package com.t3h.mediamanager1.yourfolder.video;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentYourVideoFdBinding;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.fragment.MediaListener;
import com.t3h.mediamanager1.interfaceFragment.MyFmListener;
import com.t3h.mediamanager1.models.Video;

import java.io.File;
import java.util.ArrayList;

public class FragmentMyVideo extends BaseFragment<FragmentYourVideoFdBinding> implements MediaListener<Video>,View.OnClickListener, MyFmListener {
    private TextView tvAllVideo;
    private CheckBox cbAllVideo;
    private LinearLayout llVideoFolder;

    private BaseAdapter<Video> adapter;
    private ArrayList<Video> arrVideo;
    private FileStorage fileStorage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_your_video_fd;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initFm();

    }

    private void initFm() {
        tvAllVideo = findViewById(R.id.tv_checkedAll_Vd_of_Fd);
        cbAllVideo = findViewById(R.id.cb_all_video_of_fd);
        llVideoFolder = findViewById(R.id.LL_My_Video);

        tvAllVideo.setVisibility(View.INVISIBLE);
        cbAllVideo.setVisibility(View.INVISIBLE);
        llVideoFolder.setVisibility(View.INVISIBLE);

        fileStorage = new FileStorage(getContext());

        arrVideo = fileStorage.getVideo();

        adapter = new BaseAdapter<>(getContext(),R.layout.item_video);
        binding.lvVideo1.setAdapter(adapter);
        adapter.setData(arrVideo);

        binding.setListener(this);
        adapter.setListener(this);
        cbAllVideo.setOnClickListener(this);
    }

    @Override
    public void onItemMediaClick(Video video) {
        getPlayMolelParent().setData(video.getData());
        getPlayMolelParent().showFmPlayVideo();
    }

    @Override
    public boolean onItemMediaLongClick(Video video) {


        for (Video video1 : arrVideo) {
            video1.setDisplay(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();

        tvAllVideo.setVisibility(View.VISIBLE);
        cbAllVideo.setVisibility(View.VISIBLE);
        llVideoFolder.setVisibility(View.VISIBLE);
        return false;
    }

    @Override
    public void onClickChecked(Video video) {
        if (video.getChecked()){
            video.setChecked(false);
        }else {
            video.setChecked(true);
        }
        adapter.notifyItemChanged(arrVideo.indexOf(video));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_all_video_of_fd:
                if (cbAllVideo.isChecked()){
                    for (Video video :arrVideo) {
                        video.setChecked(true);
                    }
                }else {
                    for (Video video: arrVideo) {
                        video.setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickCamera() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        getPlayMolelParent().makeVideo(intent);

        initFm();


    }

    @Override
    public void onClickDelete() {
        for (Video video: arrVideo){
            if (video.getChecked()){
                File file = new File(video.getData());
                file.delete();
            }
        }
        initFm();
    }

    @Override
    public void onClickRestore() {

    }

    @Override
    public void onClickBackup() {

    }

}
