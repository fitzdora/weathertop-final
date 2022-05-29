package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

//need to create a station list into which the readings are fed/displayed
//create an Arraylist of stations, so they can be entered or deleted in the future.

@Entity
public class Station extends Model {
    public String location;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();

    public Station(String location) {

        this.location = location;

    }
}
