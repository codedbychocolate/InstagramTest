package com.codedbychocolate.instatest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
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
public class MainActivity extends Activity {
    String CALLBACKURL = "http://www.codedbychocolate.com";
    String APIURL = "https://api.instagram.com/oauth/access_token";
    AppPrefs prefs;
    TextView error_text;
    TextView username_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        error_text = (TextView) findViewById(R.id.error_text);
        username_text = (TextView) findViewById(R.id.username_text);

        prefs = AppPrefs.getInstance();


        if(prefs.getId().equals("")){
            new GetUserData().execute();
        }
        else {
            loadUser();
        }

    }


    private void loadUser() {
        username_text.setVisibility(View.VISIBLE);
        error_text.setVisibility(View.GONE);
        username_text.setText("Hello, " + prefs.getUsername() + "!");
        getImages();
    }

    private void getImages() {
        String urlString = APIURL + "/users/"+ prefs.getId() +"/media/recent/?access_token=" + prefs.getAccessToken();

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL(urlString);
            InputStream inputStream = url.openConnection().getInputStream();
            String response = streamToString(inputStream);
            JSONObject jsonObject = (JSONObject) new JSONTokener(response).nextValue();

            JSONArray jsonArray = jsonObject.getJSONArray("data");

            Log.e("jsonArray "," " + jsonArray);

            //for(int i =0; i<=jsonArray.length(); i++) {
                //JSONObject mainImageJsonObject = jsonArray.getJSONObject(index).getJSONObject("images").getJSONObject("low_resolution");//Use for loop to traverse through the JsonArray.
                //String imageUrlString = imageJsonObject.getString("url");
           // }

            //todo move try to outside of main thread - loop through the imagejson returned and use the urls to load images into imageviews using a recyclable adapter


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    private void loadError(){
        error_text.setVisibility(View.VISIBLE);
        username_text.setVisibility(View.GONE);

    }

    class GetUserData extends AsyncTask<String, Void, String> {
        String client_id;
        String client_secret;
        String tokenURLString;
        AppPrefs prefs;
        String accessTokenString;
        String token;
        String id;
        String username;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            client_id = BuildConfig.CLIENT_ID;
            client_secret = BuildConfig.CLIENT_SECRET;
            tokenURLString = APIURL + "?client_id=" + client_id + "&client_secret=" + client_secret + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code";
        }

        @Override
        protected String doInBackground(String... params) {
            prefs = AppPrefs.getInstance();
            token = prefs.getRequestToken();

            try {
                URL url = new URL(tokenURLString);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
                outputStreamWriter.write("client_id="+client_id+
                        "&client_secret="+ client_secret +
                        "&grant_type=authorization_code" +
                        "&redirect_uri="+CALLBACKURL+
                        "&code=" + token);

                outputStreamWriter.flush();
                String response = streamToString(httpsURLConnection.getInputStream());
                JSONObject jsonObject = (JSONObject) new JSONTokener(response).nextValue();
                accessTokenString = jsonObject.getString("access_token"); //Here is your ACCESS TOKEN
                id = jsonObject.getJSONObject("user").getString("id");
                username = jsonObject.getJSONObject("user").getString("username");

            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return prefs.getAccessToken();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            prefs.setAccessToken(accessTokenString);
            prefs.setId(id);
            prefs.setUsername(username);

            Log.e("access token "," " + prefs.getAccessToken());
            Log.e("id "," " + prefs.getId());
            Log.e("username"," "  + prefs.getUsername());

            if(!prefs.getAccessToken().equals("")||prefs.getAccessToken()!=null){
                loadUser();
            }
            else {
                loadError();
            }

        }


    }

    private String streamToString(InputStream inputStream) throws IOException {
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

}