package com.cs639.petergailmcfayden.weatherwear;

import android.util.Log;

/**
 * Created by Petergail McFayden on 11/26/2014.
 */
public class GetWear {
    final String CLASS_NAME = this.getClass().getName();
    private int temperature;
    private String condition;

    private String bodyWear;
    private String footWear;
    private String accessories;

    public GetWear() {
    }

    public GetWear(int temperature, String condition) {
        this.temperature = temperature;
        this.condition = condition;
    }

    public GetWear(String bodyWear, String footWear, String accessories) {
        this.bodyWear = bodyWear;
        this.footWear = footWear;
        this.accessories = accessories;
    }

    public String getBodyWear() {
        return bodyWear;
    }

    public void setBodyWear(String bodyWear) {
        this.bodyWear = bodyWear;
    }

    public String getFootWear() {
        return footWear;
    }

    public void setFootWear(String footWear) {
        this.footWear = footWear;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public GetWear convertWeatherToWear(int temperature, String condition) {
        GetWear tempWear = new GetWear();

        if (condition == null) {
            Log.e(CLASS_NAME, "No weather provided");
            return null;
        }

        switch (condition) {
            case "Drizzle":
            case "Rain":
            case "Mist":
            case "Rain Mist":
            case "Rain Showers":
            case "Thunderstorm":
            case "Thunderstorms and Rain":
            case "Freezing Drizzle":
            case "Freezing Rain":
            case "Freezing Fog":
            case "Light Drizzle":
            case "Heavy Drizzle":
            case "Light Rain":
            case "Heavy Rain":
            case "Light Mist":
            case "Heavy Mist":
            case "Light Rain Mist":
            case "Heavy Rain Mist":
            case "Light Rain Showers":
            case "Heavy Rain Showers":
            case "Light Thunderstorm":
            case "Heavy Thunderstorm":
            case "Light Thunderstorms and Rain":
            case "Heavy Thunderstorms and Rain":
            case "Light Freezing Drizzle":
            case "Heavy Freezing Drizzle":
            case "Light Freezing Rain":
            case "Heavy Freezing Rain":
            case "Light Freezing Fog":
            case "Heavy Freezing Fog":
            case "Squalls":
                tempWear.setBodyWear("coat_raincoat");
                tempWear.setFootWear("shoes_rainboats");
                tempWear.setAccessories("accs_umbrella");
                break;

            case "Snow":
            case "Ice Crystals":
            case "Ice Pellets":
            case "Hail":
            case "Drifting Snow":
            case "Blowing Snow":
            case "Snow Showers":
            case "Snow Blowing":
            case "Snow Mist":
            case "Ice Pellet Showers":
            case "Hail Showers":
            case "Small Hail Showers":
            case "Thunderstorms and Snow":
            case "Thunderstorms and Ice Pellets":
            case "Thunderstorms with Hail":
            case "Thunderstorms with Small Hail":
            case "Light Snow":
            case "Heavy Snow":
            case "Light Snow Grains":
            case "Heavy Snow Grains":
            case "Light Ice Crystals":
            case "Heavy Ice Crystals":
            case "Light Ice Pellets":
            case "Heavy Ice Pellets":
            case "Light Hail":
            case "Heavy Hail":
            case "Light Low Drifting Snow":
            case "Heavy Low Drifting Snow":
            case "Light Blowing Snow":
            case "Heavy Blowing Snow":
            case "Light Snow Showers":
            case "Heavy Snow Showers":
            case "Light Snow Blowing Snow Mist":
            case "Heavy Snow Blowing Snow Mist":
            case "Light Ice Pellet Showers":
            case "Heavy Ice Pellet Showers":
            case "Light Hail Showers":
            case "Heavy Hail Showers":
            case "Light Small Hail Showers":
            case "Heavy Small Hail Showers":
            case "Light Thunderstorms and Snow":
            case "Heavy Thunderstorms and Snow":
            case "Light Thunderstorms and Ice Pellets":
            case "Heavy Thunderstorms and Ice Pellets":
            case "Light Thunderstorms with Hail":
            case "Heavy Thunderstorms with Hail":
            case "Light Thunderstorms with Small Hail":
            case "Heavy Thunderstorms with Small Hail":
            case "Small Hail":
                tempWear.setBodyWear("coat_coldweather");
                tempWear.setFootWear("shoes_uggs");
                tempWear.setAccessories("accs_gloves_hat_scarf");
                break;

            case "Fog":
            case "Fog Patches":
            case "Smoke":
            case "Volcanic Ash":
            case "Dust":
            case "Sand":
            case "Haze":
            case "Spray":
            case "Dust Whirls":
            case "Sandstorm":
            case "Drifting Widespread Dust":
            case "Drifting Sand":
            case "Blowing Widespread Dust":
            case "Blowing Sand":
            case "Light Fog":
            case "Heavy Fog":
            case "Light Fog Patches":
            case "Heavy Fog Patches":
            case "Light Smoke":
            case "Heavy Smoke":
            case "Light Volcanic Ash":
            case "Heavy Volcanic Ash":
            case "Light Dust":
            case "Heavy Dust":
            case "Light Sand":
            case "Heavy Sand":
            case "Light Haze":
            case "Heavy Haze":
            case "Light Spray":
            case "Heavy Spray":
            case "Light Dust Whirls":
            case "Heavy Dust Whirls":
            case "Light Sandstorm":
            case "Heavy Sandstorm":
            case "Light Low Drifting Widespread Dust":
            case "Heavy Low Drifting Widespread Dust":
            case "Light Low Drifting Sand":
            case "Heavy Low Drifting Sand":
            case "Light Blowing Widespread Dust":
            case "Heavy Blowing Widespread Dust":
            case "Light Blowing Sand":
            case "Heavy Blowing Sand":
            case "Patches of Fog":
            case "Shallow Fog":
            case "Partial Fog":
            case "Overcast":
                convertTemperatureToWear(tempWear, temperature);
                break;

            case "Clear":
            case "Partly Cloudy":
            case "Mostly Cloudy":
            case "Scattered Clouds":
            case "Funnel Cloud":
                convertTemperatureToWear(tempWear, temperature);
                break;

            case "Unknown Precipitation":
            case "Unknown":
                convertTemperatureToWear(tempWear, temperature);
                break;

        }
        return tempWear;
    }

    public GetWear convertTemperatureToWear( GetWear tempWear, int temperature) {

        if ( temperature >= 85 ) {
            tempWear.setBodyWear("summer_body_female");
            tempWear.setFootWear("shoes_flipflops");
            tempWear.setAccessories("accs_sunglasses");
        } else if ( temperature >= 75 && temperature < 85 ) {
            tempWear.setBodyWear("spring_body_female");
            tempWear.setFootWear("shoes_loafers");
            tempWear.setAccessories("accs_sunglasses");
        } else if ( temperature >= 65 && temperature < 75 ) {
            tempWear.setBodyWear("fall_body_female");
            tempWear.setFootWear("shoes_sneakerss");
            tempWear.setAccessories("accs_sunglasses");
        } else if ( temperature >= 55 && temperature < 65 ) {
            tempWear.setBodyWear("coat_trenchcoat");
            tempWear.setFootWear("shoes_uggs");
            tempWear.setAccessories("accs_sunglasses");
        } else if ( temperature < 55 ) {
            tempWear.setBodyWear("coat_coldweather");
            tempWear.setFootWear("shoes_uggs");
            tempWear.setAccessories("accs_sunglasses");
        } else {
            tempWear.setBodyWear("coat_jacket");
            tempWear.setFootWear("shoes_sneakers");
            tempWear.setAccessories("accs_sunglasses");
        }
        return tempWear;
    }
}
