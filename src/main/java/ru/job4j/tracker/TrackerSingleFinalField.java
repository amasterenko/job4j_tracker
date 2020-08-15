package ru.job4j.tracker;

public class TrackerSingleFinalField {
    private static final TrackerSingleFinalField INSTANCE = new TrackerSingleFinalField();

    private Tracker tracker = new Tracker();

    private TrackerSingleFinalField() {

    }

    public static TrackerSingleFinalField getInstance() {
        return INSTANCE;
    }

    public Tracker getTracker() {
        return tracker;
    }

}
