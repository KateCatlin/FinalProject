package com.example.katecatlin.finalproject.requests;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.katecatlin.finalproject.interfaces.IndividualApiRequestCallback;
import com.example.katecatlin.finalproject.models.ConcertModel;
import com.example.katecatlin.finalproject.parsers.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by katecatlin on 11/25/14.
 */
public class JSONRequest {

    HttpURLConnection httpURLConnection = null;
    InputStream inputStream = null;
    private static JSONRequest jsonRequest;
    IndividualApiRequestCallback thisCallback;

    public static JSONRequest getJsonRequest (IndividualApiRequestCallback individualApiRequestCallback) {
        if (jsonRequest == null) {
            jsonRequest = new JSONRequest(individualApiRequestCallback);
        }
        return jsonRequest;
    }

    private JSONRequest (IndividualApiRequestCallback individualApiRequestCallback) {
        thisCallback = individualApiRequestCallback;
    }

    public void getConcerts () {

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority("api.jambase.com")
                .appendPath("events")
                .appendQueryParameter("zipCode", "48201")
                .appendQueryParameter("radius", "10")
                .appendQueryParameter("page", "0")
                .appendQueryParameter("api_key", "fbry47pgp62uj3vnknntdyda")
                .build();

        Log.d("LOG_TAG", "URL is " + uri);

        new LoadDataInBackground().execute(uri);
    }


    private class LoadDataInBackground extends AsyncTask <Uri, Void, List<ConcertModel>> {

        private LoadDataInBackground() {
        }

        @Override
        protected List<ConcertModel> doInBackground(Uri... params) {

            try {
                Uri uri = params[0];
                JSONObject jsonObject = getJSONObjectFromUri(uri) ;
                List<ConcertModel> listOfConcerts = JSONParser.parseJSONObject(jsonObject);
                return listOfConcerts;
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
        protected void onPostExecute(List<ConcertModel> results) {
            if (results != null) {
                thisCallback.onSuccess(results);
            } else {
                thisCallback.onError();
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
