package com.t3h.mediamanager1.models;

import android.provider.MediaStore;

public class AlbumMusic extends  Model {

    @FieldInfo(fieldName = MediaStore.Audio.Albums.ALBUM_ART)
    private String image;

    @FieldInfo(fieldName = MediaStore.Audio.Albums._ID)
    private long id;

    public long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

}
