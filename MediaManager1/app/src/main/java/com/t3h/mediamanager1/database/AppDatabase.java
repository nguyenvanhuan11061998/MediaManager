package com.t3h.mediamanager1.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.t3h.mediamanager1.dao.MediaDao;
import com.t3h.mediamanager1.models.Image;
import com.t3h.mediamanager1.models.Music;
import com.t3h.mediamanager1.models.Video;


@Database(entities = {Image.class, Video.class , Music.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance = null;

    public static AppDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context
            ,AppDatabase.class
            ,"ModelManager")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract MediaDao getMediaDao();
}
