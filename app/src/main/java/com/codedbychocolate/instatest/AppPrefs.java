package com.codedbychocolate.instatest;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Niamh O'Shea on 12/09/2016.
 */
public class AppPrefs{
    protected final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private static AppPrefs instance;

    public static class Constant {
        private static final String NAME = "InstaTest";
        private static final String USER_NAME = "user_name";
        private static final String LOGGED_IN = "logged_in";
        private static final String USER_ID = "user_id";
        private static final String ACCESS_TOKEN = "access_token";
        private static final String REQUEST_TOKEN = "request_token";
    }

    public static class Default {
        private static final String USER_NAME = "";
        private static final String USER_ID = "";
        private static final boolean LOGGED_IN = false;
        private static final String ACCESS_TOKEN = "";
        private static final String REQUEST_TOKEN = "";
    }

    public AppPrefs(Context context) {
        instance = this;
        this.sharedPreferences = context.getSharedPreferences(Constant.NAME, Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
        editor.apply();
    }

    public static AppPrefs getInstance() {
        if (instance == null) throw new NullPointerException("CoreSharedHelper was not initialized!");
        return instance;
    }

/*
    protected final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private static AppPrefs instance;

    private static final String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    private static final String TOKENURL = "https://api.instagram.com/oauth/access_token";
    public static final String APIURL = "https://api.instagram.com/v1";
    public static String CALLBACKURL = "http://www.codedbychocolate.com";

    String client_id;
    String client_secret;

    String authURLString = AUTHURL + "?client_id=" + client_id + "&redirect_uri=" + CALLBACKURL + "&response_type=code&display=touch&scope=likes+comments+relationships";
    String tokenURLString = TOKENURL + "?client_id=" + client_id + "&client_secret=" + client_secret + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code"; */

    public void setUsername(String username) {
        editor.putString(Constant.USER_NAME, username);
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(Constant.USER_NAME, Default.USER_NAME);
    }


  /*  public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(Constant.LOGGED_IN, Default.LOGGED_IN);
    }

    public void setLoggedInStatus(boolean status){
        editor.putBoolean(Constant.LOGGED_IN, status);
        editor.apply();
    } */

    public void setId(String id){
        editor.putString(Constant.USER_ID, id);
        editor.apply();
    }

    public String getId(){
        return sharedPreferences.getString(Constant.USER_ID, Default.USER_ID);
    }

    public void setAccessToken(String accessToken){
        editor.putString(Constant.ACCESS_TOKEN, accessToken);
        editor.apply();
    }

    public String getAccessToken(){
        return sharedPreferences.getString(Constant.ACCESS_TOKEN, Default.ACCESS_TOKEN);
    }


    public void setRequestToken(String requestToken){
        editor.putString(Constant.REQUEST_TOKEN, requestToken);
        editor.apply();
    }

    public String getRequestToken(){
        return sharedPreferences.getString(Constant.REQUEST_TOKEN, Default.REQUEST_TOKEN);
    }

}
