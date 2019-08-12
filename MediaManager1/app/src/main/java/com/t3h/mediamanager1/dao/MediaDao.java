package com.t3h.mediamanager1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.t3h.mediamanager1.models.Image;
import com.t3h.mediamanager1.models.Music;
import com.t3h.mediamanager1.models.Video;

import java.util.List;

@Dao
public interface MediaDao {

    @Query("SELECT * FROM Image")
    List<Image> getAllImage();

    @Query("SELECT * FROM Image WHERE title = :name")
    Image getImg(String name);

    @Insert
    void insertImage(Image ... images);

    @Update
    void updateImage(Image ... images);

    @Delete
    void deleteImage(Image ... images);

    @Query("SELECT * FROM Video")
    List<Video> getAllVideo();

    @Insert
    void insertVideo(Video ... videos);

    @Update
    void updateVideo(Video ... videos);

    @Delete
    void deleteVideo(Video ... videos);

    @Query("SELECT * FROM Music")
    List<Music> getAllMusic();

    @Insert
    void insertMusic(Music ... musics);

    @Update
    void updateMusic(Music ... musics);

    @Delete
    void deleteMusic(Music ... musics);

}
