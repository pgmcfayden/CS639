package com.cs639.petergailmcfayden.weatherwear;

/**
 * Created by Petergail McFayden on 12/15/2014.
 */
public class GetWeather {

    final String DEGREE  = "\u00b0";
    final String CELSIUS = "\u2103";
    final String FAHRENHEIT = "\u2109";

    private String temperature;
    private String condition;
    private String degree;
    private String forecast_condition;

    public GetWeather() {
    }

    public GetWeather(String temperature, String condition, String degree) {
        this.temperature = temperature;
        this.condition = condition;
        setDegree(degree);
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        if (degree == "c" || degree == "C") {this.degree = CELSIUS;}
        else if (degree == "f" || degree == "F") {this.degree = FAHRENHEIT;}
        else {this.degree = DEGREE;}
    }

    public String getForecast_condition() {
        return forecast_condition;
    }

    public void setForecast_condition(String forecast_condition) {
        this.forecast_condition = forecast_condition;
    }

    @Override
    public String toString() {
        return temperature + degree + "\r\n" + condition;
    }

    public String getTestWeather() {
        String temp_temperature = "51";
        String temp_condition = "Snow";
        String temp_degree = "F";

        setTemperature(temp_temperature);
        setCondition(temp_condition);
        setDegree(temp_degree);

        return GetWeather.this.toString();
    }
}
