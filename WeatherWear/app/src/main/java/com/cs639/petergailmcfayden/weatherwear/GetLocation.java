package com.cs639.petergailmcfayden.weatherwear;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Petergail McFayden on 11/23/2014.
 *
 * https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=API_KEY
 */
public class GetLocation {

    final String API_KEY = "&key=AIzaSyCORz8jRqQjl_6z8wu59mVqEck38xa1w94";
    final String GOOGLE_API_URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";


    private String yahooWoeId;
    private String city;
    private String country;
    private int latitude;
    private int longitude;

    public GetLocation() {}

    public GetLocation(String yahooWoeId, String city, String country) {
        this.yahooWoeId = yahooWoeId;
        this.city = city;
        this.country = country;
    }

    public GetLocation(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String ConvertTo(int longitude, int latitude) {

        return null;
    }

    @Override
    public String toString() {
        return city + "," + country;
    }

    public String getTestLocation() {
        String temp_latitude = "40.7155809802915";
        String temp_longitude = "-73.9599399197085";
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection)(new URL(GOOGLE_API_URL + temp_latitude + "," + temp_longitude + API_KEY)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (Throwable throwable){}
            try {
                connection.disconnect();
            } catch (Throwable throwable){}
        }
        return null;

    }
}
