package com.t3h.mediamanager1.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.provider.MediaStore;
import android.view.View;

@Entity
public class Video extends Model {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    @FieldInfo(fieldName = MediaStore.Video.Media.DISPLAY_NAME)
    private String title;

    @ColumnInfo
    @FieldInfo(fieldName = MediaStore.Video.Media.DATA)
    private String data;

    @ColumnInfo
    private String newData;

    private boolean checked;

    private int display = View.INVISIBLE;

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
