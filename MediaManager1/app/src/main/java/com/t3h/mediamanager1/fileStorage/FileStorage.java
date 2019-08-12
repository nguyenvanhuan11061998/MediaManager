package com.t3h.mediamanager1.fileStorage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.t3h.mediamanager1.database.AppDatabase;
import com.t3h.mediamanager1.models.Image;
import com.t3h.mediamanager1.models.Music;
import com.t3h.mediamanager1.models.Video;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


public class FileStorage {
    private AppDatabase database;

    private String pathImageStorage;
    private String pathVideoStorage;
    private String pathMusicStorage;
    private Context context;

    public FileStorage(Context context) {
        createImageStore(context);
        this.context = context;
        database = AppDatabase.getInstance(context);
    }

//tạo thư mục ImageStore để lưu ảnh trong bộ nhớ trong
    private void createImageStore(Context context){
        File file1 = new File(context.getFilesDir(),"ImageStore");
        File file2 = new File(context.getFilesDir(),"VideoStore");
        File file3 = new File(context.getFilesDir(),"MusicStore");
        if (file1.exists() == false){
            file1.mkdir();
        }
        if (file2.exists() == false){
            file2.mkdir();
        }
        if (file3.exists() == false){
            file3.mkdir();
        }
        pathImageStorage = file1.getPath();
        pathVideoStorage = file2.getPath();
        pathMusicStorage = file3.getPath();
    }


// =================================================================================================
//==================================================================================================

    public static boolean copyFile(String to, String from) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(from);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(from);
                FileOutputStream fs = new FileOutputStream(to);
                byte[] buffer = new byte[2048];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                fs.close();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean deleteFile(Context context, final File file) {
        file.delete();
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(file.getPath()))));

        final String where = MediaStore.MediaColumns.DATA + "=?";
        final String[] selectionArgs = new String[] {
                file.getAbsolutePath()
        };
        final ContentResolver contentResolver = context.getContentResolver();
        final Uri filesUri = MediaStore.Files.getContentUri("external");

        contentResolver.delete(filesUri, where, selectionArgs);

        if (file.exists()) {

            contentResolver.delete(filesUri, where, selectionArgs);
        }
        return !file.exists();
    }

//==================================================================================================


    public String getPath(Uri uri){
        String filePath = null;
        Log.d("","URI = "+ uri);
        if (uri != null && "content".equals(uri.getScheme())) {
            Cursor cursor = context.getContentResolver().query(uri, new String[] { android.provider.MediaStore.Images.ImageColumns.DATA }, null, null, null);
            cursor.moveToFirst();
            filePath = cursor.getString(0);
            cursor.close();
        } else {
            filePath = uri.getPath();
        }
        Log.d("","Chosen path = "+ filePath);
        return filePath;
    }


// ================================================= Image =========================================

//lưu ảnh vừa chụp
    public void saveImage(Bitmap bitmap){
        File imgFile = new File(pathImageStorage,"image."+System.currentTimeMillis()+".jpg");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(imgFile);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//lấy danh sách ảnh trong bộ nhớ trong
    public ArrayList<Image> getImage(){
        String path = pathImageStorage;
        ArrayList<Image> arrImg = new ArrayList<>();

        File file = new File(path);
        File listFile[] = file.listFiles();

        for (File fileChil : listFile) {
            Image image = new Image();
            image.setTitle(file.getName());
            image.setChecked(false);
            image.setDisplay(View.INVISIBLE);
            image.setData(fileChil.getPath());

            arrImg.add(image);
        }
        Log.e("========= size : ",arrImg.size()+"");
        return arrImg;

    }

    public boolean moveImgToInternal(File file){
        File newImg = new File(pathImageStorage,"Image"+file.getName());
        boolean check = copyFile(newImg.getPath(), file.getPath());
        if(check){
            file.delete();
        }
        if(!file.exists()){
            return true;
        }else {
            return false;
        }
    }

    public void moveImgtoExternal(File file){
        File rootFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file1 = new File(rootFile+"/"+file.getName());
        copyFile(file1.getPath(),file.getPath());

        file.delete();
    }


// ========================================== Video ================================================

// Tạo một folder mới vào trong thư mục video
    public boolean saveVideo(Uri uri){
        String path = getPath(uri);
        File file1 = new File(path);

        File file = new File(pathVideoStorage,"video"+System.currentTimeMillis()+".mp4");
        boolean check =  copyFile(file.getPath(),path);
        if (check){
            file1.delete();
        }

        if (!file1.exists()){
            return true;
        }else {
            return false;
        }

    }


    //lấy danh sách video trong một folder
    public ArrayList<Video> getVideo(){
        ArrayList<Video> arrVideo = new ArrayList<>();

        File file = new File(pathVideoStorage);
        File listFile[] = file.listFiles();

        for (File file1: listFile){
            Video video = new Video();
            video.setTitle(file1.getName());
            video.setChecked(false);
            video.setDisplay(View.INVISIBLE);
            video.setData(file1.getPath());

            arrVideo.add(video);
        }
        return arrVideo;
    }

    public void moveVideotoExternal(File file){
        File rootFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        File file1 = new File(rootFile+"/"+file.getName());
        copyFile(file1.getPath(),file.getPath());

        file.delete();
    }


// ========================================= Music =================================================

}

