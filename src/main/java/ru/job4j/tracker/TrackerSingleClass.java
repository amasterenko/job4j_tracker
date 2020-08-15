package ru.job4j.tracker;

public class TrackerSingleClass {

    private Tracker tracker = new Tracker();

    private static final class Holder {
        private static final TrackerSingleClass INSTANCE = new TrackerSingleClass();
    }

    private TrackerSingleClass() {

    }

    public static TrackerSingleClass getInstance() {
        return Holder.INSTANCE;
    }

    public Tracker getTracker() {
        return tracker;
    }
}
