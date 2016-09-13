package com.codedbychocolate.instatest;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Niamh O'Shea on 12/09/2016.
 */
public class InstaApp {/*extends AsyncTask<URL, String>{
    private static final String TOKENURL = "https://api.instagram.com/oauth/access_token";
    public static String CALLBACKURL = "Your Redirect URI";

    String client_id = BuildConfig.CLIENT_ID;
    String client_secret = BuildConfig.CLIENT_SECRET;

    String tokenURLString = TOKENURL + "?client_id=" + client_id + "&client_secret=" + client_secret + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code";

    /*@Override
    protected Object doInBackground(Object[] params) {
        AppPrefs prefs = AppPrefs.getInstance();
        String auth_token = prefs.getAccessToken();

        try {
            URL url = new URL(tokenURLString);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
            outputStreamWriter.write("client_id="+client_id+ "&client_secret="+ client_secret + "&grant_type=authorization_code" + "&redirect_uri="+CALLBACKURL+ "&code=" + auth_token);
            outputStreamWriter.flush();
            String response = streamToString(httpsURLConnection.getInputStream());
            JSONObject jsonObject = (JSONObject) new JSONTokener(response).nextValue();
            prefs.setAccessToken(jsonObject.getString("access_token"));
            prefs.setId(jsonObject.getJSONObject("user").getString("id"));
            prefs.setUsername(jsonObject.getJSONObject("user").getString("username"));

            Log.e("access token "," " + prefs.getAccessToken());
            Log.e("id "," " + prefs.getId());
            Log.e("username"," "  + prefs.getUsername());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
*/
    /*private String streamToString(InputStream inputStream) throws IOException {
        String string = "";

        if (inputStream != null) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                inputStream.close();
            }

            string = stringBuilder.toString();
        }

        return string;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        AppPrefs prefs = AppPrefs.getInstance();
        String auth_token = prefs.getAccessToken();

        try {
            URL url = new URL(tokenURLString);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
            outputStreamWriter.write("client_id="+client_id+ "&client_secret="+ client_secret + "&grant_type=authorization_code" + "&redirect_uri="+CALLBACKURL+ "&code=" + auth_token);
            outputStreamWriter.flush();
            String response = streamToString(httpsURLConnection.getInputStream());
            JSONObject jsonObject = (JSONObject) new JSONTokener(response).nextValue();
            prefs.setAccessToken(jsonObject.getString("access_token"));
            prefs.setId(jsonObject.getJSONObject("user").getString("id"));
            prefs.setUsername(jsonObject.getJSONObject("user").getString("username"));

            Log.e("access token "," " + prefs.getAccessToken());
            Log.e("id "," " + prefs.getId());
            Log.e("username"," "  + prefs.getUsername());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }*/
}
