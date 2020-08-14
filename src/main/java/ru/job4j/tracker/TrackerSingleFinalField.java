package ru.job4j.tracker;

public class TrackerSingleFinalField {
    private static final TrackerSingleFinalField INSTANCE = new TrackerSingleFinalField();

    private Tracker tracker = new Tracker();

    public static TrackerSingleFinalField getInstance() {
        return INSTANCE;
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public Item[] findAll() {
        return tracker.findAll();
    }

    Item[] findByName(String key) {
        return  tracker.findByName(key);
    }

    public Item findById(int id) {
        return tracker.findById(id);
    }

    public boolean replace(int id, Item item) {
        return tracker.replace(id, item);
    }

    public boolean delete(int id) {
        return tracker.delete(id);
    }

}
