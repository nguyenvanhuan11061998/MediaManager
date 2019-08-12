package com.t3h.mediamanager1.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.provider.MediaStore;
import android.view.View;

@Entity
public class Music extends Model {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.TITLE)
    private String title;

    @ColumnInfo
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.DATA)
    private String data;

    @ColumnInfo
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.DURATION)
    private String duration;

    @ColumnInfo
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.ARTIST)
    private String artist;

    @ColumnInfo
    @FieldInfo(fieldName = MediaStore.Audio.AudioColumns.ALBUM_ID)
    private long albumId;

    private boolean checked = false;

    @ColumnInfo
    private String newData;
    private int display = View.INVISIBLE;

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
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
