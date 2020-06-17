package com.t3h.mediamanager1;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AppBinding {

    @BindingAdapter("thumb")
    public static void setThumb(ImageView im, String img){
        Glide.with(im)
                .load(img)
                .error(R.drawable.ic_image_not_data)
                .into(im);
    }

    public static ImageView getThumb(ImageView im, String img){
        Glide.with(im)
                .load(img)
                .error(R.drawable.failed_img)
                .into(im);

        return im;
    }

}
