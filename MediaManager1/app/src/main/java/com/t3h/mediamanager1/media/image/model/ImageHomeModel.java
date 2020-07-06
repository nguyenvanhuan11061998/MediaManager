package com.t3h.mediamanager1.media.image.model;

import android.net.Uri;

import com.t3h.mediamanager1.media.image.contract.ImageHomeContract;
import com.t3h.mediamanager1.models.Model;

public class ImageHomeModel extends Model implements ImageHomeContract.Model{
    private String title;
    private Uri uri;
    private long imageSize;
    private long date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public long getImageSize() {
        return imageSize;
    }

    public void setImageSize(long imageSize) {
        this.imageSize = imageSize;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
