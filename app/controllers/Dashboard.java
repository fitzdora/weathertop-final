package controllers;

import java.util.List;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;


public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Station> stations = member.stations;
        render("dashboard.html", stations, member);

    }

    public static void deleteStation(Long id, Long stationid) {
        Member member = Accounts.getLoggedInMember();
        Station station = Station.findById(stationid);
        member.stations.remove(station);
        member.save();
        station.delete();
        Logger.info("Removing " + station.location);
        redirect("/dashboard");
    }

    public static void addStation(String location) {
        Member member = Accounts.getLoggedInMember();
        Station station = new Station(location);
        member.stations.add(station);
        member.save();
        Logger.info("Adding a new station called " + location);
        redirect("/dashboard");
    }
}
