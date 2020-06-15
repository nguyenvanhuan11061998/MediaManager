package com.t3h.mediamanager1.dao;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareHelper {

     public enum Keys {
        PASSWORD,
        HINT
    }

    private SharedPreferences share;

    public ShareHelper(Context context) {
        share = context.getSharedPreferences("SharePreferences",Context.MODE_PRIVATE);
    }

    public String get(Keys k, String defautValues){
        return share.getString(k.toString(),defautValues);
    }

    public void set(Keys k, String values){
        SharedPreferences.Editor editor = share.edit();
        editor.putString(k.toString(),values);
        editor.commit();
    }

    public void  remove(Keys ... keys){
        SharedPreferences.Editor editor = share.edit();
        for (Keys k: keys){
            editor.remove(k.toString());
        }
        editor.commit();
    }

    /**
     * key save infor login
     */
    public enum KeysUserModel {
        USERNAME,
        PASSWORD,
        PHONE,
        EMAIL,
        HINT
    }
    public String getKeyUserModel(KeysUserModel k, String defaultValue){
        return share.getString(k.toString(),defaultValue);
    }

    public void setKeyUserModel(KeysUserModel k, String defaultValue){
        SharedPreferences.Editor editor = share.edit();
        editor.putString(k.toString(),defaultValue);
        editor.commit();
    }

    public void removeKeyUserModel (KeysUserModel ... keysUserModels){
        SharedPreferences.Editor editor = share.edit();
        for (KeysUserModel k: keysUserModels) {
            editor.remove(k.toString());
        }
        editor.commit();
    }

}
