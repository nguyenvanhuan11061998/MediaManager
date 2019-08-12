package com.t3h.mediamanager1.view;

import androidx.lifecycle.Observer;
import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.t3h.mediamanager1.App;
import com.t3h.mediamanager1.activity.MainActivity;
import com.t3h.mediamanager1.activity.PlayModelActivity;
import com.t3h.mediamanager1.databinding.UiPlayMusicBinding;
import com.t3h.mediamanager1.interfaceFragment.MusicPlayListener;
import com.t3h.mediamanager1.service.MusicService;

public class MusicPlayView extends FrameLayout implements MusicPlayListener{

    private UiPlayMusicBinding binding;
    private App app;

    public MusicPlayView(Context context) {
        super(context);

        init();
    }

    public MusicPlayView(Context context,AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public MusicPlayView( Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public MusicPlayView(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init() {
        binding = UiPlayMusicBinding.inflate(LayoutInflater.from(getContext()));
        addView(binding.getRoot());

        app = (App) getContext().getApplicationContext();
        setVisibility(GONE);

        binding.setListener(this);                                                                                      //gọi sự kiện click cho
    }

    public void registerStateMainAct(){
        MainActivity act = (MainActivity) getContext();

        app.getService().getIsLife().observe(act, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean){
                    setVisibility(VISIBLE);
                }else {
                    setVisibility(GONE);
                }
            }
        });

        app.getService().getName().observe(act, new Observer<String>() {                                    //lấy ra tên bài hát hiện tại đang phát để đưa lên MP3PlayView
            @Override
            public void onChanged(@Nullable String s) {
                binding.setName(s);                                                                         //đưa tên bài hát vào binding đưa sang layout
            }
        });

        app.getService().getIsPlaying().observe(act, new Observer<Boolean>() {                              //lấy ra trạng thái phát nhạc của bài hát để hiển thị lên layout
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                binding.setIsPlaying(aBoolean);
            }
        });
    }

    public void registerStatePlayAct(){
        PlayModelActivity act = (PlayModelActivity) getContext();

        app.getService().getIsLife().observe(act, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean){
                    setVisibility(VISIBLE);
                }else {
                    setVisibility(GONE);
                }
            }
        });

        app.getService().getName().observe(act, new Observer<String>() {                                    //lấy ra tên bài hát hiện tại đang phát để đưa lên MP3PlayView
            @Override
            public void onChanged(@Nullable String s) {
                binding.setName(s);                                                                         //đưa tên bài hát vào binding đưa sang layout
            }
        });

        app.getService().getIsPlaying().observe(act, new Observer<Boolean>() {                              //lấy ra trạng thái phát nhạc của bài hát để hiển thị lên layout
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                binding.setIsPlaying(aBoolean);
            }
        });
    }

    @Override
    public void play() {
        if (app.getService().getIsPlaying().getValue() == true){                                       //nếu trạng thái phát nhạc đang bằng true thì :
            app.getService().pause();                                                                  //chuyển sang pause
        }else {
            app.getService().startPlay();                                                                  //nếu trạng thái đang bằng false thì tức là đang pause, thì chuyển sang play
        }
    }

    @Override
    public void prev() {
        app.getService().change(MusicService.PREV);
    }

    @Override
    public void next() {
        app.getService().change(MusicService.NEXT);
    }
}
