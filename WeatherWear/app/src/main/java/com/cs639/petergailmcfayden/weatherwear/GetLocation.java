package com.cs639.petergailmcfayden.weatherwear;

/**
 * Created by Petergail McFayden on 11/23/2014.
 */
public class GetLocation {

    private String yahooWoeId;
    private String city;
    private String country;

    public GetLocation() {}

    public GetLocation(String yahooWoeId, String city, String country) {
        this.yahooWoeId = yahooWoeId;
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return city + "," + country;
    }
}
