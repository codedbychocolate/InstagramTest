package com.codedbychocolate.instatest;

import android.app.Application;
import android.content.Context;

/**
 * Created by Pat O'Shea on 12/09/2016.
 */
public class App extends Application{
    static AppPrefs prefs;
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        prefs = new AppPrefs(context);
    }

    public static AppPrefs getPrefsInstance() {

        if (prefs == null) {
            prefs = new AppPrefs(context);
        }

        return prefs;
    }
}

