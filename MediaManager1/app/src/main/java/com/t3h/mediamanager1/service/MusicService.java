package com.t3h.mediamanager1.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import androidx.lifecycle.MutableLiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.IntDef;
import androidx.core.app.NotificationCompat;

import android.widget.RemoteViews;

import com.t3h.mediamanager1.R;
import com.t3h.mediamanager1.media.music.MusicModel;

import java.util.ArrayList;

public class MusicService extends Service implements MediaPlayer.OnCompletionListener {

    private MediaPlayer player;
    private ArrayList<MusicModel> arrMusic = new ArrayList<>();
    private int curentIndex;

    public static final int NEXT = 1;
    public static final int PREV = -1;

    private MutableLiveData<Boolean> isPlaying = new MutableLiveData<>();                           //lấy trạng thái playing để hiển thị button pause/play
    private MutableLiveData<String> name = new MutableLiveData<>();                                 //lấy tên bài hát đang hiển thị hiện tại
    private MutableLiveData<Boolean> isLife = new MutableLiveData<>();                              //lấy trạng thái có bài hát nào đang trong danh sách phát hay không
    public MutableLiveData<Boolean> getIsLife() {
        return isLife;
    }
    public MutableLiveData<Boolean> getIsPlaying() {
        return isPlaying;
    }
    public MutableLiveData<String> getName() {
        return name;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder(this);
    }


    public class MusicBinder extends Binder{
        private MusicService service;

        public MusicBinder(MusicService service) {
            this.service = service;
        }

        public MusicService getService() {
            return service;
        }
    }


    public void setArrMusic(ArrayList<MusicModel> arrMusic) {
        this.arrMusic = arrMusic;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerBroadcastReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        realese();
        unregisterReceiver(receiver);
    }



// ================= các phương thức chơi nhạc =====================================================

    public void create(final int index){
        realese();

        try {
            player = new MediaPlayer();
            final String data = arrMusic.get(index).getData();
            player.setDataSource(data);


            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {                                            //trong hàm này có thể play bài hát
                    player.setOnCompletionListener(MusicService.this);

                    curentIndex = index;
                    name.postValue(arrMusic.get(index).getName());
                    isLife.postValue(true);

                    startPlay();
                }
            });

            player.prepareAsync();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
    }

    public void startPlay() {
        if (player != null){
            player.start();
            isPlaying.postValue(true);
            pushNotification();
        }
    }

    public void pause(){
        if(player != null){
            player.pause();
            isPlaying.postValue(false);
            pushNotification();
        }
    }

    public void realese(){
        if (player != null){
            player.release();
            isPlaying.postValue(false);
        }
    }

    @IntDef({PREV,NEXT})
    public @interface TypeChange{
    }

    public void change(@TypeChange int value){
        curentIndex += value;
        if (curentIndex >= arrMusic.size()){
            curentIndex = 0;
        }else if (curentIndex <0){
            curentIndex = arrMusic.size()-1;
        }
        create(curentIndex);
    }


// ===================================== notification ==============================================
    private  void pushNotification(){
        Intent intent = new Intent(this,getClass());
        startService(intent);

        String CHANNEL_ID = "CHANNEL_ID";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O  ) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, CHANNEL_ID,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }


        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.ui_notification);
        remoteViews.setTextViewText(R.id.tv_name_music,arrMusic.get(curentIndex).getName());

        if (player.isPlaying()){
            remoteViews.setImageViewResource(R.id.im_state_play,R.drawable.ic_play);
        }else{
            remoteViews.setImageViewResource(R.id.im_state_play,R.drawable.ic_pause);
        }

        setAction(remoteViews,R.id.im_close,ACTION_MUSIC_EXIT);
        setAction(remoteViews,R.id.im_state_play,ACTION_MUSIC_PLAY);
        setAction(remoteViews,R.id.im_prev,ACTION_MUSIC_PREV);
        setAction(remoteViews,R.id.im_next,ACTION_MUSIC_NEXT);

        NotificationCompat.Builder builder =                                                        //taọ notification
                new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_music,2);                                                  //set icon
        builder.setCustomBigContentView(remoteViews);                                               //set View

        startForeground(1111111, builder.build());                                              //chạy foreground service phát nhạc, gắn thêm notification

    }


// ===================================== Broadcast Receiver =========================================


    private final String ACTION_MUSIC_PLAY = "action.music.play";
    private final String ACTION_MUSIC_PREV = "action.music.prev";
    private final String ACTION_MUSIC_NEXT = "action.music.next";
    private final String ACTION_MUSIC_EXIT = "action.music.exit";

    private void registerBroadcastReceiver(){

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_MUSIC_PLAY);
        filter.addAction(ACTION_MUSIC_NEXT);
        filter.addAction(ACTION_MUSIC_PREV);
        filter.addAction(ACTION_MUSIC_EXIT);

        registerReceiver(receiver,filter);
    }

    private void setAction(RemoteViews remoteViews, int ResID, String action) {
        Intent intent = new Intent(action);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0, intent, 0);
        remoteViews.setOnClickPendingIntent(ResID,pendingIntent);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case ACTION_MUSIC_PLAY:
                    if (player.isPlaying()){
                        pause();
                    }else {
                        startPlay();
                    }
                    break;

                case ACTION_MUSIC_PREV:
                    change(PREV);
                    break;

                case ACTION_MUSIC_NEXT:
                    change(NEXT);
                    break;

                case ACTION_MUSIC_EXIT:
                    stopForeground(true);
                    realese();
                    isLife.postValue(false);
                    stopSelf();
                    break;
            }
        }
    };

}
