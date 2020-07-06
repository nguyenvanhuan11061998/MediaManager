package com.t3h.mediamanager1.media.music;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseAdapter;
import com.t3h.mediamanager1.base.BaseFragment;
import com.t3h.mediamanager1.databinding.FragmentMusicBinding;
import com.t3h.mediamanager1.fragment.MediaListener;
import com.t3h.mediamanager1.media.music.adapter.MusicAdapter;
import com.t3h.mediamanager1.media.music.model.MusicModel;
import com.t3h.mediamanager1.models.Music;

import java.util.ArrayList;

public class FragmentMusic extends BaseFragment<FragmentMusicBinding> implements MediaListener<Music>, View.OnClickListener, MusicAdapter.onClickItemMusic {

    private MusicAdapter adapter;
    ArrayList<MusicModel> arrMusic;
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

        arrMusic = systemData.getMusicLocal();
        adapter = new MusicAdapter(getContext(),arrMusic);
        binding.lvMusic.setAdapter(adapter);
        cbAllMusic.setOnClickListener(this);

        adapter.setClickItemMusic(this);
    }

    @Override
    public void onItemMediaClick(Music music) {
        app.getService().setArrMusic(adapter.getArrMusic());
        int index = adapter.getArrMusic().indexOf(music);
        app.getService().create(index);
    }

    @Override
    public boolean onItemMediaLongClick(Music music) {

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
    }


    @Override
    public void onClickItemMusic(MusicModel musicModel) {
        app.getService().setArrMusic(adapter.getArrMusic());
        int index = adapter.getArrMusic().indexOf(musicModel);
        app.getService().create(index);
    }
}
