package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

//sets the initial weather readings
@Entity
public class Reading extends Model {
    public int weatherCode;
    public double tempC;
    public double windSpeed;
    public int pressure;
    public double windDirection;


    //Constructors
    //constructor with Parameters
    public Reading(int weatherCode, double tempC, double windSpeed, int pressure, double windDirection) {

        this.weatherCode = weatherCode;
        this.tempC = tempC;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;

    }
}
