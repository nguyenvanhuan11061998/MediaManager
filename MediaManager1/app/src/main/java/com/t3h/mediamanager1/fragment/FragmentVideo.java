package com.t3h.mediamanager1.fragment;

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
import com.t3h.mediamanager1.interfaceFragment.ClickFmListener;
import com.t3h.mediamanager1.models.Video;

import java.io.File;
import java.util.ArrayList;


public class FragmentVideo extends BaseFragment<FragmentVideoBinding> implements MediaListener<Video>,  View.OnClickListener, ClickFmListener {

    public static final String EXTRA_PLAY_VIDEO = "extra_play_video";
    private BaseAdapter<Video> adapter;
    private TextView tvCheckedAll;
    private CheckBox cbAllVideo;
    private LinearLayout ll_fm_video;

    private ArrayList<Video> arrVideo;
    private NewFolderDialog newFolderDialog;
    private FileStorage fileStorage;

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
        adapter = new BaseAdapter<>(getContext(),R.layout.item_video);
        binding.lvVideo.setAdapter(adapter);

        arrVideo = systemData.getVideo();

        adapter.setData(arrVideo);
        adapter.setListener(this);
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

        for (Video video1: arrVideo) {
            video1.setDisplay(View.VISIBLE);
        }
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
        switch (v.getId()){
            case R.id.cb_all_vid:
                if (cbAllVideo.isChecked()){
                    for (Video video: arrVideo) {
                        video.setChecked(true);
                    }
                } else {
                    for (Video video: arrVideo) {
                        video.setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onClickHilder() {
        boolean check = false;
        for (Video video : arrVideo) {
            if (video.getChecked()){
                File file = new File(video.getData());
                fileStorage.moveImgToInternal(file);
                check = true;
            }
        }
        if(check){
            Toast.makeText(getContext(),"Đã dấu video", Toast.LENGTH_LONG).show();
            initFm();
        }else {
            Toast.makeText(getContext(),"Chọn video để dấu",Toast.LENGTH_LONG).show();
            return;
        }
    }

    @Override
    public void onClickDelete() {
        boolean check = false;
        for (Video video : arrVideo) {
            if (video.getChecked()){
                File file = new File(video.getData());
                check = true;
                fileStorage.deleteFile(getContext(),file);
            }
        }
        if (check == true){
            Toast.makeText(getContext()," Đã xóa video", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(),"Chọn video để xóa",Toast.LENGTH_LONG).show();
            return;
        }
        initFm();
    }
}
