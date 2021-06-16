package ru.job4j.tracker.store;

public class TrackerSingleFinalField {
    private static final TrackerSingleFinalField INSTANCE = new TrackerSingleFinalField();

    private Store tracker = new SqlTracker();

    private TrackerSingleFinalField() {

    }

    public static TrackerSingleFinalField getInstance() {
        return INSTANCE;
    }

    public Store getTracker() {
        return tracker;
    }

}
