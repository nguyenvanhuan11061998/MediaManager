package com.t3h.mediamanager1.media.music;

import android.net.Uri;

public class MusicModel implements MusicContract.Model {
    private String name;
    private long duration;
    private long date;
    private Uri uri;
    private long size;
    private String artist;
    private long albumId;
    private String data;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Uri getUri() {
        return uri == null ? Uri.parse("") : uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getArtist() {
        return artist == null ? "" : artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }
}
