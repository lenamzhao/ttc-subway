package main.java;

import java.time.LocalTime;
import java.util.TreeSet;

public class Stop {
    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST;
    }

    private Direction direction;
    private TreeSet<LocalTime> schedules;

    // Constructor
    public Stop(Direction direction) {
        this.direction = direction;
        this.schedules = new TreeSet<>();
    }

    public TreeSet<LocalTime> getSchedules() {
        return schedules;
    }

    public void scheduleTime(LocalTime time) {
        schedules.add(time);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        String result = "";
        for (LocalTime time : schedules) {
            result +=  direction.toString() + " Bound Arrival Time: " + time.toString() + "\n";
        }
        return result;
    }

}

