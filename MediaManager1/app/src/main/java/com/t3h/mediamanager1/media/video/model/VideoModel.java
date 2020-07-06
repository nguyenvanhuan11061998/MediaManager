package com.t3h.mediamanager1.media.video.model;

import android.net.Uri;

import com.t3h.mediamanager1.media.video.contract.VideoContract;

public class VideoModel implements VideoContract.Model {
    private String title;
    private Uri uri;
    private long videoSize;
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

    public long getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(long videoSize) {
        this.videoSize = videoSize;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
