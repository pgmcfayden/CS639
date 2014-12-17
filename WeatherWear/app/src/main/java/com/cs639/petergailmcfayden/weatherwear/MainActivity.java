package com.cs639.petergailmcfayden.weatherwear;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Petergail McFayden on 11/5/2014.
 */
public class MainActivity extends Activity{

    final String CLASS_NAME = this.getClass().getName();
    private ImageDownloader imageDownloader;

    int longitude;
    int latitude;
    int i = 0;
    String temperature;
    String forecast;
    String condition;

    String image_url;
    String image_title;
    String image_link;
    String display_location_full;
    String weather;  //condition
    String temperature_string;
    String temp_f; //temperature
    String icon;
    String icon_url;

    int bodyID;
    int feetID;
    int accessoriesID;

    // Test values need to delte
    double test_lat = 129.000;
    double test_long = 49.000;
    int test_temperature = 51;
    String test_forecast = "snow";

    GetGPSLocation gpsLocation = new GetGPSLocation(0, 0);
    GetWundergroundWeather wundergroundWeather = new GetWundergroundWeather();
    JSONObject weatherResult = new JSONObject();  //JSONData returned by website
    JSONArray images;
    JSONArray display_locations;
    GetWear whatToWear = new GetWear();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_page);
        Log.d(CLASS_NAME,"Activity Created");

        LocationManager manager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(location != null) {
                    longitude = (int) location.getLongitude();
                    latitude = (int) location.getLatitude();
                } else {
                    longitude = (int) test_long;
                    latitude = (int) test_lat;
//                    gpsLocation.ConvertToYahooWoeId(longitude,latitude);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0,listener);
        try {
            weatherResult = wundergroundWeather.getWundergroundWeatherForecast(latitude, longitude);
            Log.d(CLASS_NAME,"Weather generated");
            images = weatherResult.getJSONArray("image");
            for (i = 0; i < images.length(); i++) {
                JSONObject image = images.getJSONObject(i);
                image_url = image.getString("url");
                image_title = image.getString("title");
                image_link = image.getString("link");
            }
            display_locations = weatherResult.getJSONArray("display_location");
            for (i = 0; i < display_locations.length(); i++) {
                JSONObject display_location = display_locations.getJSONObject(i);
                display_location_full = display_location.getString("full");
            }
            weather = weatherResult.getString("weather");
            temperature_string = weatherResult.getString("temperature_string");
            temp_f = weatherResult.getString("temp_f");
            icon = weatherResult.getString("icon");
            icon_url = weatherResult.getString("icon_url");
        }catch (JSONException exp){
            Log.e(CLASS_NAME, "JSON Array throws an error");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        whatToWear.convertWeatherToWear(Integer.parseInt(temp_f),weather);
        Log.d(CLASS_NAME,"Wear generated");

        TextView weather_forecast = (TextView)findViewById(R.id.weather_forecast);
        weather_forecast.setText(temperature_string+"\n"+weather);

        imageDownloader = new ImageDownloader();
        imageDownloader.execute(icon_url);

        bodyID = getResources().getIdentifier(whatToWear.getBodyWear(),"drawable",getPackageName());
        ImageView wear_body = (ImageView)findViewById(bodyID);
        wear_body.setImageResource(bodyID);

        feetID = getResources().getIdentifier(whatToWear.getFootWear(),"drawable",getPackageName());
        ImageView wear_feet = (ImageView)findViewById(feetID);
        wear_feet.setImageResource(feetID);

        accessoriesID = getResources().getIdentifier(whatToWear.getBodyWear(),"drawable",getPackageName());
        ImageView wear_accessories = (ImageView)findViewById(accessoriesID);
        wear_accessories.setImageResource(accessoriesID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.main_activity);
        Log.d(CLASS_NAME,"Activity Started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (imageDownloader != null) {
            imageDownloader.cancel(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(CLASS_NAME,"Activity Destroyed");

    }

    private class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {
        ProgressDialog message;

        @Override
        protected void onPreExecute() {
            message = ProgressDialog.show(MainActivity.this, "Fetching image, please wait!", null);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Log.i("ImageDownloader", "Going to fetch image");
            publishProgress(1);
            try {
                URL url = new URL(params[0]);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

                if (httpCon.getResponseCode() != 200) {
                    throw new Exception("Failed to connect");
                }

                InputStream is = httpCon.getInputStream();
                publishProgress(0);
                return BitmapFactory.decodeStream(is);
            } catch (Exception exp) {
                Toast.makeText(MainActivity.this, exp.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ImageDownloader", "Didn't work!", exp);
                Log.e("ImageDownloader", exp.getMessage());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //super.onPostExecute(bitmap);
            ImageView iv = (ImageView) findViewById(R.id.weather_image);
            if (iv != null && bitmap != null) {
                iv.setImageBitmap(bitmap);
            }
            message.dismiss();
            message = null;
            Log.i("ImageDownloader", "Image icon fetched");
        }

    }
}
