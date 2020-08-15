package ru.job4j.tracker;

public enum TrackerSingleEnum {
    INSTANCE;

    private Tracker tracker;

    public Tracker getTracker() {
        return tracker;
    }
}
