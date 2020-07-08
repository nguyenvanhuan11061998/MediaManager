package com.t3h.mediamanager1.media.music.play_music;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.base.BaseActivityNew;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

class PlayMusicFragment extends BaseActivityNew {
    @BindView(R.id.img_background_image)
    CircleImageView backgroundImageImageView;

    @Override
    protected int getLayoutAct() {
        return R.layout.fragment_play_music;
    }

    @Override
    protected void intAct() {
        roundImageMusic(true);
    }

    private void roundImageMusic(boolean isRounding) {
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(500);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatMode(Animation.RELATIVE_TO_SELF);
        rotate.setRepeatCount(11);
        backgroundImageImageView.startAnimation(rotate);
    }
}
