package com.codedbychocolate.instatest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Niamh O'Shea on 12/09/2016.
 */
public class LoginActivity extends Activity {
    final String TAG = "LoginActivity";
    private static final String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    private static final String TOKENURL = "https://api.instagram.com/oauth/access_token";
    public static final String APIURL = "https://api.instagram.com/v1";
    public static String CALLBACKURL = "http://www.codedbychocolate.com";
   // String authURLString = AUTHURL + "?client_id=" + BuildConfig.CLIENT_ID + "&redirect_uri=" + CALLBACKURL + "&response_type=code&display=touch&scope=likes+comments+relationships";
    String authURLString = AUTHURL + "?client_id=" + BuildConfig.CLIENT_ID + "&redirect_uri=" + CALLBACKURL + "&response_type=code&display=touch&scope=likes+comments+relationships";
    String tokenURLString = TOKENURL + "?client_id=" + BuildConfig.CLIENT_ID + "&client_secret=" + BuildConfig.CLIENT_SECRET + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code";
    AppPrefs prefs;
    boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = AppPrefs.getInstance();

        if(prefs.getRequestToken().equals("")){

            WebView login_webview = new WebView(getApplicationContext());
            login_webview.setVerticalScrollBarEnabled(false);
            login_webview.setHorizontalScrollBarEnabled(false);
            login_webview.getSettings().setJavaScriptEnabled(true);
            login_webview.getSettings().setDomStorageEnabled(true);
            login_webview.setWebViewClient(new AuthWebViewClient());
            login_webview.loadUrl(authURLString);
            setContentView(login_webview);
        }
        else {
            setContentView(R.layout.activity_logout);
        }

    }

    private class AuthWebViewClient extends WebViewClient {
        String request_token;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            AppPrefs prefs = AppPrefs.getInstance();
            request_token = "";

            Log.e("url ","" + url );
            /*if (url.startsWith(CALLBACKURL)) {
                Log.e("CALLBACK","");

                if (url.contains("access_token")) {
                    String parts[] = url.split("=");
                    request_token = parts[1];
                    Log.e("request token ", " " + request_token);
                    prefs.setRequestToken(request_token);

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else if (url.contains("error_reason")) {
                    String error = url.contains("user_denied") ? "User denied access" : "Authentication failed";
                    Toast.makeText(getApplicationContext(), "COMPUTER SAYS NO " + error, Toast.LENGTH_SHORT).show();
                    finish();
                }

            }*/

            if (url.startsWith(CALLBACKURL)) {
                System.out.println(url);
                String parts[] = url.split("=");
                request_token = parts[1];

                Log.e("request token", request_token);

                if(!request_token.equals("")){
                    prefs.setRequestToken(request_token);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }

                return true;
            }

            return false;
        }

    }

}
