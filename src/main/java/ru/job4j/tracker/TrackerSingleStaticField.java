package ru.job4j.tracker;

public class TrackerSingleStaticField {
    private static TrackerSingleStaticField instance;

    private Tracker tracker = new Tracker();

    private TrackerSingleStaticField() {
    }

    public static TrackerSingleStaticField getInstance() {
        if (instance == null) {
            instance = new TrackerSingleStaticField();
        }
        return instance;
    }

    public Tracker getTracker() {
        return tracker;
    }

}
