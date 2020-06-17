package com.t3h.mediamanager1.api;

public class ApiUtils {
    public static final String BASE_URL = "http://192.168.10.121/mediaManager/";

    public static MediaApi getDataApi(){
        return ApiBuilder.getApi(BASE_URL).create(MediaApi.class);
    }
}
