package utils;


import models.Reading;

import java.util.List;

public class StationAnalytics {

    public static Reading getLatestReading(List<Reading> readings) {
        Reading latestReading = null;
        if (readings.size() > 0) {
            latestReading = readings.get(readings.size() - 1);

        }
        return latestReading;
    }

    public static double windChill(double tempC, double windSpeed) {
        double chill;
        double windVelocity = Math.pow(windSpeed, 0.16);
        chill = 13.12 + 0.6215 * tempC - 11.37 * windVelocity + 0.3965 * tempC * windVelocity;
        return Math.round(chill);
    }

}
