package com.example.katecatlin.finalproject.requests;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.katecatlin.finalproject.interfaces.JsonApiCallback;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by katecatlin on 11/25/14.
 */
public class JSONRequest {

    HttpURLConnection httpURLConnection = null;
    InputStream inputStream = null;


    private static JSONRequest jsonRequest;

    public static JSONRequest getJsonRequest () {
        if (jsonRequest == null) {
            jsonRequest = new JSONRequest();
        }

        return jsonRequest;
    }


    private JSONRequest () {
    }


    public void getConcerts (JsonApiCallback jsonApiCallback) {

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("api.jambase.com")
                .appendPath("events")
                .appendQueryParameter("zipCode", "48201")
                .appendQueryParameter("radius", "10")
                .appendQueryParameter("page", "0")
                .appendQueryParameter("api_key", "fbry47pgp62uj3vnknntdyda")
                .build();

        new LoadDataInBackground(jsonApiCallback).execute(uri);
    }


    private class LoadDataInBackground extends AsyncTask <Uri, Void, JSONObject> {

        private JsonApiCallback jsonApiCallback;

        private LoadDataInBackground(JsonApiCallback jsonApiCallback) {
            this.jsonApiCallback = jsonApiCallback;
        }

        @Override
        protected JSONObject doInBackground(Uri... params) {

            try {
                Uri uri = params[0];
                return getJSONObjectFromUri(uri);
            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                return null;
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    Log.d("LOG_TAG", "IOException on closing input stream");
                }

            }
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            if (result != null) {
                this.jsonApiCallback.onSuccess();
            } else {
                this.jsonApiCallback.onError();
            }
        }
    }

    private JSONObject getJSONObjectFromUri(Uri uri) throws IOException, JSONException {
        String uriString = uri.toString();
        URL url = new URL(uriString);

        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();

        inputStream = httpURLConnection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        int bytesRead;
        StringBuilder stringBuilder = new StringBuilder();

        while ((bytesRead = bufferedInputStream.read()) != -1) {
            stringBuilder.append((char)bytesRead);
        }

        return new JSONObject(stringBuilder.toString());


    }



}
