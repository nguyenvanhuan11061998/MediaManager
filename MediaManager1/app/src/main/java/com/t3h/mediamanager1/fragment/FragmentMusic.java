package com.t3h.mediamanager1.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentMusicBinding;
import com.t3h.mediamanager1.models.Music;

import java.util.ArrayList;

public class FragmentMusic extends BaseFragment<FragmentMusicBinding> implements MediaListener<Music>, View.OnClickListener {

    private BaseAdapter<Music> adapter;
    ArrayList<Music> arrMusic;
    private TextView tvAllMusic;
    private CheckBox cbAllMusic;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_music;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvAllMusic = findViewById(R.id.tv_checked_all_music);
        cbAllMusic = findViewById(R.id.cb_all_music);

        adapter = new BaseAdapter<>(getContext(),R.layout.item_music);
        binding.lvMusic.setAdapter(adapter);

        arrMusic = systemData.getMusic();

        adapter.setData(arrMusic);
        adapter.setListener(this);
        cbAllMusic.setOnClickListener(this);
    }

    @Override
    public void onItemMediaClick(Music music) {
        app.getService().setArrMusic(adapter.getData());
        int index = adapter.getData().indexOf(music);
        app.getService().create(index);
    }

    @Override
    public boolean onItemMediaLongClick(Music music) {

        tvAllMusic.setVisibility(View.VISIBLE);
        cbAllMusic.setVisibility(View.VISIBLE);

        for (Music music1 : arrMusic){
            music1.setDisplay(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();

        return false;
    }

    @Override
    public void onClickChecked(Music music) {

        if (music.getChecked() == true){
            music.setChecked(false);
        }else {
            music.setChecked(true);
        }
        adapter.notifyItemChanged(arrMusic.indexOf(music));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_all_music:
                if (cbAllMusic.isChecked()){
                    for (Music music : arrMusic) {
                        music.setChecked(true);
                    }
                }else {
                    for (Music music: arrMusic) {
                        music.setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }


}
