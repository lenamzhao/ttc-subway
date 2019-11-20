package main.java;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private String name;
    private List<Stop> stops;

    // Constructor
    public Station(String name) {
        this.name = name;
        this.stops = new ArrayList<>();
    }

    public void addStop(Stop stop) {
        stops.add(stop);
    }

    public List<Stop> getStops() {
        return stops;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}