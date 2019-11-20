package main.java;
import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class Subway {
    private List<Line> lines;

    // Constructor
    public Subway() {
        this.lines = new ArrayList<Line>();
    }

    public void addLine(Line line) {
        lines.add(line);
    }


    // Main Method
    public static void main(String[] args) throws IOException {

        // build a subway system
        Subway ttc = buildSubwaySystem();

        // REQUIREMENT #1
        // Print out the next train arrival time by a given station and direction
        ttc.getNextStopTime("king", LocalTime.of(8, 21), Stop.Direction.NORTH);

        //  REQUIREMENT #2
        //  Print out a station's schedule for which train will arrive at that certain time
        ttc.getStopsByStation("st george", true);

    } // end of main()

    // REQUIREMENT #1 - Get the next train arrival time by a given station and direction
    public void getNextStopTime(String station, LocalTime time, Stop.Direction direction) {
        TreeSet<LocalTime> schedule = new TreeSet<>();

        System.out.println("***** STATION: " + station.toUpperCase()  + " *****");

        // get the schedule by the given station and direction
        if (!getStopsByStation(station, false).isEmpty()) {
            for (Stop stop : getStopsByStation(station, false)) {
                if (stop.getDirection().equals(direction)){
                    schedule.addAll(stop.getSchedules());
                }
            }
            // get the next time in schedule
            LocalTime target = schedule.ceiling(time);

            if (target != null) {
                System.out.println("Next Train Arrival Time: " + schedule.ceiling(time) + "\n");
            }
            else {
                System.out.println("ERROR: Invalid DIRECTION for this station! Please enter a correct direction.");
            }
        }
        else {
            System.out.println("ERROR: Invalid STATION NAME! Please enter a correct station name.");
        }
    } // end of getNextStopTime()



    // REQUIREMENT #2 - Get all train arrival times by a give station name
    public List<Stop> getStopsByStation(String name, boolean print) {
        List<Stop> result = new ArrayList<>();

        if (print)
            System.out.println("***** STATION: " + name.toUpperCase()  + " *****");

        // look through each line to find the station
        for (Line line: lines) {
            for (Station station: line.getStations()) {
                if (station.getName().equalsIgnoreCase(name)) {

                    if (print) {
                        System.out.println("[ Line: " + line.getLineName().name() + " ]");
                        // print out the stops
                        printSchedule(station.getStops());
                    }
                    // add all the stops
                    result.addAll(station.getStops());
                }
            }
        }
        return result;
    } // end of getStopsByStation


    // Print all the schedule time by stops after the current time
    public static void printSchedule(List<Stop> stops) {
        for (Stop stop : stops) {
            System.out.println("========== Station Schedule ===========");
            System.out.println(stop.toString());
        }
    }

    /*
     * Helper method - get all the stops from a station
     */
    public List<Stop> getAllStops() {
        List<Stop> result = new ArrayList<>();
        for (Line line: lines) {
            for (Station station: line.getStations()) {
                System.out.println("STATION: " + station.getName());
                System.out.println(("STOPS:  " + station.getStops().toString()));
                result.addAll(station.getStops());
            }
        }
        return result;
    }

    /*
     * Helper method - Building a TTC Subway System
     */
    public static Subway buildSubwaySystem() throws IOException {

        Subway ttc = new Subway();

        List<String> lineOneYongeStationNames = new ArrayList<>(Arrays.asList(
                "NORTH",
                "Union",
                "King",
                "Queen",
                "Dundas",
                "College",
                "Wellesley",
                "Bloor-Yonge",
                "Rosedale",
                "Summerhill",
                "St Clair",
                "Davisville",
                "Eglinton",
                "Lawrence",
                "York Mills",
                "Sheppard-Yonge ",
                "North York Centre",
                "Finch"));

        List<String> lineOneVaughanStationNames = new ArrayList<>(Arrays.asList(
                "SOUTH",
                "Vaughan Metropolitan Centre",
                "Highway 407",
                "Pioneer Village",
                "York University",
                "Finch West",
                "Downsview Park",
                "Sheppard West",
                "Wilson",
                "Yorkdale",
                "Lawrence West",
                "Glencairn",
                "Eglinton West",
                "St Clair West",
                "Dupont",
                "Spadina",
                "St George",
                "Museum",
                "Queen's Park",
                "St Patrick",
                "Osgoode",
                "St Andrew",
                "Union"));

        List<String> lineTwoStationNames = new ArrayList<>(Arrays.asList(
                "EAST",
                "Kipling",
                "Islington",
                "Royal York",
                "Old Mill",
                "Jane",
                "Runnymede ",
                "High Park ",
                "Keele",
                "Dundas West",
                "Lansdowne ",
                "Dufferin",
                "Ossington",
                "Christie",
                "Bathurst",
                "Spadina",
                "St George",
                "Bay",
                "Bloor-Yonge",
                "Sherbourne ",
                "Castle Frank ",
                "Broadview",
                "Chester ",
                "Pape",
                "Donlands ",
                "Greenwood ",
                "Coxwell",
                "Woodbine",
                "Main Street",
                "Victoria Park",
                "Warden ",
                "Kennedy"));

        List<String> lineThreeStationNames = new ArrayList<>(Arrays.asList(
                "WEST",
                "Kennedy",
                "Lawrence East ",
                "Ellesmere ",
                "Midland ",
                "Scarborough  ",
                "McCowan"));

        List<String> lineFourStationNames = new ArrayList<>(Arrays.asList(
                "East",
                "Sheppard-Yonge ",
                "Bayview",
                "Bessarion",
                "Leslie",
                "Don Mills"));

        // add Line 1 - Yonge-University
        Line lineOne = new Line(Line.Lines.ONE);
        // add Line 2 - Bloor-Danforth
        Line lineTwo = new Line(Line.Lines.TWO);
        // add Line 3 - Scarborough
        Line lineTree = new Line(Line.Lines.THREE);
        // add Line 2 - Sheppard
        Line lineFour = new Line(Line.Lines.FOUR);

        // build subway line 1 via Yonge
        buildSubwayLine(lineOneYongeStationNames.remove(0), lineOne, lineOneYongeStationNames,
                LocalTime.of(5, 54), LocalTime.of(13, 45),
                LocalTime.of(6, 03), LocalTime.of(13, 51));

        // build subway line 1 via Vaughan
        buildSubwayLine(lineOneVaughanStationNames.remove(0), lineOne, lineOneVaughanStationNames,
                LocalTime.of(6, 03), LocalTime.of(13, 15),
                LocalTime.of(5, 51), LocalTime.of(13, 31));

        // build subway line 2
        buildSubwayLine(lineTwoStationNames.remove(0), lineTwo, lineTwoStationNames,
                LocalTime.of(6, 03), LocalTime.of(13, 15),
                LocalTime.of(5, 51), LocalTime.of(13, 31));

        // build subway line 3
        buildSubwayLine(lineThreeStationNames.remove(0), lineTree, lineThreeStationNames,
                LocalTime.of(6, 03), LocalTime.of(13, 15),
                LocalTime.of(5, 51), LocalTime.of(13, 31));

        // build subway line 4
        buildSubwayLine(lineFourStationNames.remove(0), lineFour, lineFourStationNames,
                LocalTime.of(5, 39), LocalTime.of(14, 33),
                LocalTime.of(5, 33), LocalTime.of(14, 22));

        // add subway lines to ttc
        ttc.addLine(lineOne);
        ttc.addLine(lineTwo);
        ttc.addLine(lineTree);
        ttc.addLine(lineFour);

        return ttc;
    } // end of buildSubwaySystem()


    /*
     * Helper method - Generate a list of times increment of every 5 mins
     */
    public static List<LocalTime> generateSchedule (LocalTime startTime, LocalTime endTime) {
        List<LocalTime> result = new ArrayList<>();

        // add 5 mins interval from startTime to endTime
        while (startTime.compareTo(endTime) <= 0) {
            result.add(startTime);
            startTime = startTime.plusMinutes(30);
        }
        return result;
    }

    /*
     * Helper method - Generates the schedule and adds to the stop
     */
    public static Stop addSchedulesToStop(Stop stop, LocalTime startTime, LocalTime endTime) {
        List<LocalTime> schedules = generateSchedule(startTime, endTime);
        for (LocalTime schedule : schedules) {
            stop.scheduleTime(schedule);
        }
        return stop;
    }


    /*
     * Helper method - Build a subway line given a direction, line, stationNames, first and last train times
     */
    public static void buildSubwayLine(String direction, Line line, List<String> stationNames,
                                       LocalTime firstTrainXBound, LocalTime lastTrainXBound,
                                       LocalTime firstTrainYBound, LocalTime lastTrainYBound) {

        for (int i=0; i<stationNames.size(); i++) {
            boolean dirXAdded, dirYAdded;
            dirXAdded = dirYAdded = false;

            Station currStation = new Station(stationNames.get(i).trim());

            // special case - terminal station
            if (i == 0) {
                if (direction.equalsIgnoreCase(Stop.Direction.NORTH.toString())) {
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.NORTH), firstTrainXBound, lastTrainXBound));
                    dirXAdded = true;
                }
                else if (direction.equalsIgnoreCase(Stop.Direction.EAST.toString())) {
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.EAST), firstTrainXBound, lastTrainXBound));
                    dirXAdded = true;
                }
                else if (direction.equalsIgnoreCase(Stop.Direction.SOUTH.toString())) {
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.SOUTH), firstTrainYBound, lastTrainYBound));
                    dirYAdded = true;
                }
                else if (direction.equalsIgnoreCase(Stop.Direction.WEST.toString())) {
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.WEST), firstTrainYBound, lastTrainYBound));
                    dirYAdded = true;
                }
            }
            else if (i == stationNames.size() - 1) {
                if (direction.equalsIgnoreCase(Stop.Direction.NORTH.toString())) {
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.SOUTH), firstTrainYBound, lastTrainYBound));
                    dirYAdded = true;
                }
                else if (direction.equalsIgnoreCase(Stop.Direction.SOUTH.toString())) {
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.NORTH), firstTrainXBound, lastTrainXBound));
                    dirXAdded = true;
                }
                else if (direction.equalsIgnoreCase(Stop.Direction.EAST.toString())) {
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.WEST), firstTrainYBound, lastTrainYBound));
                    dirYAdded = true;
                }
                else if (direction.equalsIgnoreCase(Stop.Direction.WEST.toString())) {
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.EAST), firstTrainXBound, lastTrainXBound));
                    dirXAdded = true;
                }
            }
            // Non-terminal stations
            else {
                if (direction.equalsIgnoreCase(Stop.Direction.NORTH.toString()) ||
                        direction.equalsIgnoreCase(Stop.Direction.SOUTH.toString())) {
                    // add northbound and southbound stops
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.NORTH), firstTrainXBound, lastTrainXBound));
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.SOUTH), firstTrainYBound, lastTrainYBound));
                    dirXAdded = dirYAdded = true;
                }
                else if (direction.equalsIgnoreCase(Stop.Direction.EAST.toString()) ||
                        direction.equalsIgnoreCase(Stop.Direction.WEST.toString())) {
                    // add eastbound and westbound stops
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.EAST), firstTrainXBound, lastTrainXBound));
                    currStation.addStop(addSchedulesToStop(new Stop(Stop.Direction.WEST), firstTrainYBound, lastTrainYBound));
                    dirXAdded = dirYAdded = true;
                }
            }

            // add 2 mins for next station time window
            if (dirXAdded) {
                firstTrainXBound = firstTrainXBound.plusMinutes(20);
                lastTrainXBound = lastTrainXBound.plusMinutes(20);
            }
            if (dirYAdded) {
                firstTrainYBound = firstTrainYBound.plusMinutes(20);
                lastTrainYBound = lastTrainYBound.plusMinutes(20);
            }

            // add station names to the subway Line
            line.addStation(currStation);
        }
    } // end of buildSubwayLine()

}