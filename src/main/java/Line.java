package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Line {
    public enum Lines {
        ONE ("Yonge-University"),
        TWO ("Bloor-Danforth"),
        THREE ("Scarborough"),
        FOUR ("Sheppard");

        private String name;
        Lines(String name){
            this.name = name;
        }
    }

    private Lines lineName;
    private Map<Integer, Station> stations;

    // Constructor
    public Line(Lines name) {
        this.lineName = name;
        stations = new TreeMap<Integer, Station>();
    }

    public Lines getLineName() {
        return lineName;
    }

    public void addStation(Station station) {
        // auto sign keys
        stations.put(stations.size(), station);
    }

    public List<Station> getStations() {
        return new ArrayList<Station>(stations.values());
    }
}