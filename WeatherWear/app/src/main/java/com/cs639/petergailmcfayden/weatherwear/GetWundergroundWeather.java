package com.cs639.petergailmcfayden.weatherwear;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Petergail McFayden on 11/26/2014.
 */
public class GetWundergroundWeather {

    final String WUNDERGROUND_API_KEY = "542e792185b9e21f";
    final String WUNDERGROUND_URL = "http://api.wunderground.com/api/542e792185b9e21f/conditions/q/";
    final String WUNDERGROUND_URL_TEST = "http://api.wunderground.com/api/542e792185b9e21f/conditions/q/37.776289,-122.395234.json";
    final String WUNDERGROUND_URL_EXT = ".json";
    //http://api.wunderground.com/api/542e792185b9e21f/conditions/q/CA/San_Francisco.json
    //http://api.wunderground.com/api/542e792185b9e21f/conditions/q/37.776289,-122.395234.json
    //http://www.wunderground.com/cgi-bin/findweather/getForecast?query=37.773285,-122.417725
    final int CONNECTION_TIMEOUT = 0;
    final int DATARETRIEVAL_TIMEOUT = 0;
    final String CLASS_NAME = this.getClass().getName();
    ProgressDialog message;
    private WeatherDownloader weatherDownloader;
    private JSONObject weatherJSON;


    public GetWundergroundWeather() {
    }

    public JSONObject getWundergroundWeatherForecast(int latitude, int longitude) {
        Log.i("GetWundergroundWeather", "Going to fetch weather");
        weatherDownloader = new WeatherDownloader();
        weatherDownloader.execute(String.valueOf(latitude),String.valueOf(longitude));
        return weatherJSON;
    }

    private static String getWeatherResponseText(InputStream inputStream) {
        return new Scanner(inputStream).useDelimiter("\\A").next();
    }

    private class WeatherDownloader extends AsyncTask<String, Integer, JSONObject> {
        ProgressDialog message;
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            Log.i("WeatherDownloader", "Going to fetch weather");
            publishProgress(1);
            try {
                URL url = new URL(WUNDERGROUND_URL+params[0]+","+params[1]+WUNDERGROUND_URL_EXT);
                urlConnection = (HttpURLConnection) url.openConnection();

                if (urlConnection.getResponseCode() != 200) {
                    throw new Exception("Failed to connect");
                }

                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                weatherJSON = new JSONObject(getWeatherResponseText(inputStream));
                return weatherJSON;

            } catch (MalformedURLException malExp) {
                Log.e(CLASS_NAME, "URL is incorrect");
            } catch (ProtocolException proExp) {
                Log.e(CLASS_NAME, "Website down");
            } catch (IOException ioExp) {
                Log.e(CLASS_NAME, "Could not read in any JSON");

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (Throwable throwable){}
                try {
                    urlConnection.disconnect();
                } catch (Throwable throwable){}
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Log.i("WeatherDownloader", "Weather was fetched");
        }
    }
}
