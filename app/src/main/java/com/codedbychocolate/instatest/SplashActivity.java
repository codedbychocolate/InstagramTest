package com.codedbychocolate.instatest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Pat O'Shea on 13/09/2016.
 */
public class SplashActivity extends Activity {
        AppPrefs prefs;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            whereDoWeGoNow();
        }

        private void whereDoWeGoNow() {
            prefs = App.getPrefsInstance();
            boolean userIsLoggedIn = isUserLoggedIn();

            if (userIsLoggedIn) {
                startActivity(new Intent(this, MainActivity.class));
            }
            else {
                startActivity(new Intent(this, LoginActivity.class));
            }

        }

    private boolean isUserLoggedIn() {
        return !prefs.getRequestToken().equals("");
    }

}
