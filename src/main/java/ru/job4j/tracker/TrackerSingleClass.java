package ru.job4j.tracker;

public class TrackerSingleClass {

    private Store tracker = new SqlTracker();

    private static final class Holder {
        private static final TrackerSingleClass INSTANCE = new TrackerSingleClass();
    }

    private TrackerSingleClass() {

    }

    public static TrackerSingleClass getInstance() {
        return Holder.INSTANCE;
    }

    public Store getTracker() {
        return tracker;
    }
}
