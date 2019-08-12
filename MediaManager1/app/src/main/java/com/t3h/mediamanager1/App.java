package com.t3h.mediamanager1;



import android.app.Application;

import com.t3h.mediamanager1.service.MusicService;

public class App extends Application {

    private MusicService service;

    public MusicService getService() {
        return service;
    }

    public void setService(MusicService service) {
        this.service = service;
    }
}
