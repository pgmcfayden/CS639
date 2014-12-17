package com.cs639.petergailmcfayden.weatherwear;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Petergail McFayden on 11/23/2014.
 */
public class GetYahooWeather {

    private static String YAHOO_WEATHER_RSS_URL = "http://weather.yahooapis.com/forecastrss?p=";
    private String yahooWoeIdn;
    //http://weather.yahooapis.com/forecastrss?p=location

    public String YAHOO_APPID = "YAHOO_APP_ID";

    public GetYahooWeather() {
    }

    public String getYahooWeatherForecast(String yahooWoeId) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection)(new URL (YAHOO_WEATHER_RSS_URL + yahooWoeId)).openConnection();
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
