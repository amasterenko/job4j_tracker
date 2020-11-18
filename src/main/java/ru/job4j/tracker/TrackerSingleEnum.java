package ru.job4j.tracker;

public enum TrackerSingleEnum {
    INSTANCE;

    private Store tracker;

    public Store getTracker() {
        return tracker;
    }
}
