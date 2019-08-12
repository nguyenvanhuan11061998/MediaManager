package com.t3h.mediamanager1.dao;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.t3h.mediamanager1.models.AlbumMusic;
import com.t3h.mediamanager1.models.FieldInfo;
import com.t3h.mediamanager1.models.Image;
import com.t3h.mediamanager1.models.Model;
import com.t3h.mediamanager1.models.Music;
import com.t3h.mediamanager1.models.Video;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SystemData {

    private ContentResolver resolver;

    public SystemData(Context context) {
        resolver = context.getContentResolver();
    }

    private void read(Cursor cursor) {
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.e(cursor.getColumnName(i), cursor.getString(i) + " ");
            }
            Log.e("=========", "=================");
            cursor.moveToNext();
        }
    }


    private ArrayList<AlbumMusic> getAlbum(){
        Cursor cursor = resolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,null,null,null,null);
        ArrayList<AlbumMusic> arr = getData(cursor,AlbumMusic.class);

        Log.e("=========== ",arr.size()+"");

        return arr;
    }

    public ArrayList<Image> getImages(){
        Cursor cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        ArrayList<Image> arr = getData(cursor,Image.class);
        return arr;
    }

    public ArrayList<Image> getMyImages(){
        Cursor cursor = resolver.query(MediaStore.Images.Media.INTERNAL_CONTENT_URI,null,null,null,null);
        ArrayList<Image> arr = getData(cursor,Image.class);
        return arr;
    }

    public ArrayList<Video> getVideo(){
        Cursor cursor = resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        ArrayList<Video> arr = getData(cursor,Video.class);
        return arr;
    }

    public ArrayList<Video> getMyVideo(){
        Cursor cursor = resolver.query(MediaStore.Video.Media.INTERNAL_CONTENT_URI,null,null,null,null);
        ArrayList<Video> arr = getData(cursor,Video.class);
        return arr;
    }

    public ArrayList<Music> getMusic(){
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        ArrayList<Music> arr = getData(cursor,Music.class);

        ArrayList<AlbumMusic> arrAlbum = getAlbum();

        for (Music m:arr) {
            for (AlbumMusic a:arrAlbum) {
                if (m.getAlbumId() == a.getId()){
                    m.setImage(a.getImage());
                }
            }
        }
        return arr;
    }

    private <T extends Model> ArrayList<T> getData(Cursor cursor, Class<T> clz) {
        ArrayList<T> arr = new ArrayList<>();
        try {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                // tạo ra đối tượng bằng cách gọi constructor rỗng
                T t = clz.newInstance();
                // lấy toàn bộ thuộc tính của đối tượng
                Field[] fields = clz.getDeclaredFields();

                for (Field f : fields) {
                    // lấy annotation của trường
                    FieldInfo info = f.getAnnotation(FieldInfo.class);
                    // nếu k có annotation thì đọc sang field tiếp theo
                    if (info == null) continue;
                    // lấy dữ liệu từ database
                    int index = cursor.getColumnIndex(info.fieldName());
                    String value = cursor.getString(index);
                    // set dữ liệu vào cho object
                    setData(f, t, value);
                }
                arr.add(t);
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return arr;
    }

    private <T extends Model> void setData(Field f, T t, String value) throws IllegalAccessException {
        f.setAccessible(true);
        // lấy ra kiểu dữ liệu của thuộc tính
        String typeName = f.getType().getSimpleName();

        if (typeName.equalsIgnoreCase("int")) {
            f.setInt(t, Integer.parseInt(value));
            return;
        }
        if (typeName.equalsIgnoreCase("long")) {
            f.setLong(t, Long.parseLong(value));
            return;
        }
        if (typeName.equalsIgnoreCase("float")) {
            f.setFloat(t, Float.parseFloat(value));
            return;
        }
        if (typeName.equalsIgnoreCase("double")) {
            f.setDouble(t, Double.parseDouble(value));
            return;
        }
        if (typeName.equalsIgnoreCase("boolean")) {
            f.setBoolean(t, Boolean.parseBoolean(value));
            return;
        }
        // set cho kiểu dữ liệu đối tượng
        f.set(t, value);
    }
}
