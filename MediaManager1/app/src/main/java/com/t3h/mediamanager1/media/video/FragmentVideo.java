package com.t3h.mediamanager1.media.video;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentVideoBinding;
import com.t3h.mediamanager1.dialog.NewFolderDialog;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.fragment.MediaListener;
import com.t3h.mediamanager1.interfaceFragment.ClickFmListener;
import com.t3h.mediamanager1.media.video.adapter.VideoAdapter;
import com.t3h.mediamanager1.media.video.model.VideoModel;
import com.t3h.mediamanager1.models.Video;

import java.io.File;
import java.util.ArrayList;


public class FragmentVideo extends BaseFragment<FragmentVideoBinding> implements MediaListener<Video>,  View.OnClickListener, ClickFmListener {

    public static final String EXTRA_PLAY_VIDEO = "extra_play_video";
    private TextView tvCheckedAll;
    private CheckBox cbAllVideo;
    private LinearLayout ll_fm_video;

    private ArrayList<VideoModel> arrVideo;
    private NewFolderDialog newFolderDialog;
    private FileStorage fileStorage;
    private VideoAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFm();

    }

    private void initFm() {
        tvCheckedAll = findViewById(R.id.tv_checkedAllVideo);
        cbAllVideo = findViewById(R.id.cb_all_vid);
        ll_fm_video = findViewById(R.id.ll_fm_video);

        tvCheckedAll.setVisibility(View.INVISIBLE);
        cbAllVideo.setVisibility(View.INVISIBLE);
        ll_fm_video.setVisibility(View.INVISIBLE);

        fileStorage = new FileStorage(getContext());
        arrVideo = systemData.getVideoLocal();
        adapter = new VideoAdapter(getContext(),arrVideo);
        binding.lvVideo.setAdapter(adapter);
        binding.setListener(this);

        newFolderDialog = new NewFolderDialog(getContext());

        cbAllVideo.setOnClickListener(this);
    }

    @Override
    public void onItemMediaClick(Video video) {
        String data = video.getData();
        Intent intent = new Intent(getContext(), PlayModelActivity.class);
        intent.putExtra(EXTRA_PLAY_VIDEO,data);
        intent.putExtra(getParent().EXTRA_INDEX_FM,2);
        startActivity(intent);
    }

    @Override
    public boolean onItemMediaLongClick(Video video) {
        tvCheckedAll.setVisibility(View.VISIBLE);
        cbAllVideo.setVisibility(View.VISIBLE);

        adapter.notifyDataSetChanged();


        return false;
    }

    @Override
    public void onClickChecked(Video video) {
        if (video.getChecked() == false){
            video.setChecked(true);
        } else {
            video.setChecked(false);
        }
        adapter.notifyItemChanged(arrVideo.indexOf(video));

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClickHilder() {

    }

    @Override
    public void onClickDelete() {

    }
}
