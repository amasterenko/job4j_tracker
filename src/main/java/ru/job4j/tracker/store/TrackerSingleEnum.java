package ru.job4j.tracker.store;

import ru.job4j.tracker.store.Store;

public enum TrackerSingleEnum {
    INSTANCE;

    private Store tracker;

    public Store getTracker() {
        return tracker;
    }
}
