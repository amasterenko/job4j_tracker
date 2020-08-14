package ru.job4j.tracker;

public class TrackerSingleClass {

    private Tracker tracker = new Tracker();

    private static final class Holder {
        private static final TrackerSingleClass INSTANCE = new TrackerSingleClass();
    }

    public static TrackerSingleClass getInstance() {
        return Holder.INSTANCE;
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
