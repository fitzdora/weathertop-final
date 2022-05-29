package controllers;

import java.util.List;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

public class StationCtrl extends Controller {
    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);
        /** call to util method to output the latest readings**/
        Reading latestReading = StationAnalytics.getLatestReading(station.readings);
        String conditions = latestConditions(latestReading.weatherCode);
        /** call to method to output the Fahrenheit reading**/
        double f = changeTemp(latestReading.tempC);
        /** call to  method to output the beaufort scale **/
        int beaufortScale = bScale(latestReading.windSpeed);
        /** call to method to read text output for beaufort label - double test my understanding**/
        String beaufortLabel = windConditions(latestReading.windSpeed);
        /**call to Compass Method**/
        String compass = windCompass(latestReading.windDirection);
        /** call out to windchill method placed in Station Analytics to test**/
        double chill = StationAnalytics.windChill(latestReading.tempC, latestReading.windSpeed);

        render("stationlist.html", station, latestReading, f, conditions, beaufortScale, beaufortLabel, compass, chill);
    }

    public static void deleteReading(Long id, Long readingid) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing " + reading.weatherCode);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        render("stationlist.html", station);
    }

    public static void addReading(Long id, int weatherCode, double tempC, double windSpeed, double windDirection, int pressure) {
        Reading reading = new Reading(weatherCode, tempC, windSpeed, pressure, windDirection);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }

    public static double changeTemp(double tempC) {
        double f;
        f = (tempC * 1.8) + 32;
        return Math.round(f);
    }

    public static String latestConditions(int code) {
        switch (code) {
            case 100:
                return "Clear";
            case 200:
                return "Partial Clouds";
            case 300:
                return "Cloudy";
            case 400:
                return "Light Showers";
            case 500:
                return "Heavy Showers";
            case 600:
                return "Rain";
            case 700:
                return "Snow";
            case 800:
                return "Thunder";
        }
        return "unknown";
    }

    public static int bScale(double windSpeed) {

        if (windSpeed <= 1) {
            return 0;
        } else if ((windSpeed >= 1) && (windSpeed <= 5)) {
            return 1;
        } else if ((windSpeed >= 6) && (windSpeed <= 11)) {
            return 2;
        } else if ((windSpeed >= 12) && (windSpeed <= 19)) {
            return 3;
        } else if ((windSpeed >= 20) && (windSpeed <= 28)) {
            return 4;
        } else if ((windSpeed >= 29) && (windSpeed <= 38)) {
            return 5;
        } else if ((windSpeed >= 39) && (windSpeed <= 49)) {
            return 6;
        } else if ((windSpeed >= 50) && (windSpeed <= 61)) {
            return 7;
        } else if ((windSpeed >= 62) && (windSpeed <= 74)) {
            return 8;
        } else if ((windSpeed >= 75) && (windSpeed <= 88)) {
            return 9;
        } else if ((windSpeed >= 89) && (windSpeed <= 102)) {
            return 10;
        } else if ((windSpeed >= 103) && (windSpeed <= 117)) {
            return 11;
        } else
            return 0;
    }

    public static String windConditions(double windSpeed) {
        if (windSpeed <= 1) {
            return "Calm";
        } else if ((windSpeed >= 1) && (windSpeed <= 5)) {
            return "Light Air";
        } else if ((windSpeed >= 6) && (windSpeed <= 11)) {
            return "Light Breeze";
        } else if ((windSpeed >= 12) && (windSpeed <= 19)) {
            return "Gentle Breeze";
        } else if ((windSpeed >= 20) && (windSpeed <= 28)) {
            return "Moderate Breeze";
        } else if ((windSpeed >= 29) && (windSpeed <= 38)) {
            return "Fresh Breeze";
        } else if ((windSpeed >= 39) && (windSpeed <= 49)) {
            return "Strong Breeze";
        } else if ((windSpeed >= 50) && (windSpeed <= 61)) {
            return "Near Gale";
        } else if ((windSpeed >= 62) && (windSpeed <= 74)) {
            return "Gale";
        } else if ((windSpeed >= 75) && (windSpeed <= 88)) {
            return "Severe Gale";
        } else if ((windSpeed >= 89) && (windSpeed <= 102)) {
            return "Strong Storm";
        } else if ((windSpeed >= 103) && (windSpeed <= 117)) {
            return "Violent Storm";
        } else {
            return "Run like hell it's a Tornado!";
        }
    }

    public static String windCompass(double windDirection) {
        if (windDirection <= 11.25) {
            return "North";
        } else if ((windDirection >= 11.25) && (windDirection <= 33.75)) {
            return "North North East";
        } else if ((windDirection >= 33.75) && (windDirection < 56.25)) {
            return "North East";
        } else if ((windDirection >= 56.25) && (windDirection < 78.25)) {
            return "East North East";
        } else if ((windDirection >= 78.25) && (windDirection < 101.25)) {
            return "East";
        } else if ((windDirection >= 101.25) && (windDirection < 123.75)) {
            return "East South East";
        } else if ((windDirection >= 123.75) && (windDirection < 146.25)) {
            return "South East";
        } else if ((windDirection >= 146.25) && (windDirection < 168.75)) {
            return "South South East";
        } else if ((windDirection >= 168.75) && (windDirection < 191.25)) {
            return "South";
        } else if ((windDirection >= 191.25) && (windDirection < 213.75)) {
            return "South South West";
        } else if ((windDirection >= 213.75) && (windDirection < 236.25)) {
            return "South West";
        } else if ((windDirection >= 236.25) && (windDirection < 258.75)) {
            return "West South West";
        } else if ((windDirection >= 258.75) && (windDirection < 281.25)) {
            return "West";
        } else if ((windDirection >= 281.25) && (windDirection < 303.75)) {
            return "West North West";
        } else if ((windDirection >= 303.75) && (windDirection < 326.25)) {
            return "North West";
        } else if ((windDirection >= 326.25) && (windDirection < 348.75)) {
            return "North North West";
        } else if (windDirection >= 348.75) {
            return "North";
        } else {
            return "No Wind today!";
        }


    }
}
