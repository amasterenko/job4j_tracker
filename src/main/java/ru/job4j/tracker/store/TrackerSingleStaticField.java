package ru.job4j.tracker.store;

public class TrackerSingleStaticField {
    private static TrackerSingleStaticField instance;

    private Store tracker = new SqlTracker();

    private TrackerSingleStaticField() {
    }

    public static TrackerSingleStaticField getInstance() {
        if (instance == null) {
            instance = new TrackerSingleStaticField();
        }
        return instance;
    }

    public Store getTracker() {
        return tracker;
    }

}
