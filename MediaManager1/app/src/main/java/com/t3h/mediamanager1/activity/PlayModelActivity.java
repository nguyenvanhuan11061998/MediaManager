package com.t3h.mediamanager1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Toast;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivity;
import com.t3h.mediamanager1.databinding.ActivityPlayImageBinding;
import com.t3h.mediamanager1.fileStorage.FileStorage;
import com.t3h.mediamanager1.fragment.FragmentMyImg;
import com.t3h.mediamanager1.fragment.FragmentImage;
import com.t3h.mediamanager1.fragment.FragmentImagePlay;
import com.t3h.mediamanager1.fragment.FragmentMyVideo;
import com.t3h.mediamanager1.fragment.FragmentVideo;
import com.t3h.mediamanager1.fragment.FragmentVideoPlay;


public class PlayModelActivity extends BaseActivity<ActivityPlayImageBinding>{


    public static final String EXTRA_INDEX_FM = "extra_index_fm";
    public static final int REQUEST_PLAY_MODEL = 2;

    public static final int REQUEST_TAKE_PHOTO = 10;
    public static final int REQUEST_MAKE_VIDEO = 11;

    private FragmentImagePlay fmPlayImg = new FragmentImagePlay();
    private FragmentMyImg fmMyImg = new FragmentMyImg();
    private FragmentMyVideo fmMyVideo = new FragmentMyVideo();
    private FragmentVideoPlay fmPlayVideo = new FragmentVideoPlay();
    private Fragment getCurrentFm;


    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    protected void initAct() {

        binding.musicPlayView1.registerStatePlayAct();

        Intent intent = getIntent();
        int index = intent.getIntExtra(MainActivity.EXTRA_INDEX_FM,0);
        if (index == 3){
            showFmMyImg();
        } else if (index == 4){
            showFmMyVideo();
        } else if (index == 1){
            data = intent.getStringExtra(FragmentImage.EXTRA_PLAY_IMAGE);
            showFmPlayImg();
        } else if (index == 2){
            data = intent.getStringExtra(FragmentVideo.EXTRA_PLAY_VIDEO);
            showFmPlayVideo();
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_image;
    }

    private void showFmMyImg(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel_playImg,fmMyImg);
        getCurrentFm = fmMyImg;
        transaction.commitAllowingStateLoss();
    }

    private void showFmMyVideo() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel_playImg,fmMyVideo);
        getCurrentFm = fmMyVideo;
        transaction.commitAllowingStateLoss();
    }

    public void showFmPlayImg(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel_playImg,fmPlayImg);
        getCurrentFm = fmPlayImg;
        transaction.commitAllowingStateLoss();
    }

    public void showFmPlayVideo(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.panel_playImg,fmPlayVideo);
        getCurrentFm = fmPlayVideo;
        transaction.commitAllowingStateLoss();
    }

    public void takePhoto(Intent intent){
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }

    public void makeVideo(Intent intent){
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(intent, REQUEST_MAKE_VIDEO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_TAKE_PHOTO){
                FileStorage fileStorage = new FileStorage(this);
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                fileStorage.saveImage(bp);

                fmMyImg = new FragmentMyImg();
                showFmMyImg();


        }else if (requestCode == REQUEST_MAKE_VIDEO && resultCode == RESULT_OK){
            Uri uri = data.getData();
            FileStorage fileStorage = new FileStorage(this);
            boolean check =  fileStorage.saveVideo(uri);
            if (check){
                Toast.makeText(this," Một video mới được tạo",Toast.LENGTH_LONG).show();
                fmMyVideo = new FragmentMyVideo();
                showFmMyVideo();
            }
        }
    }

}
