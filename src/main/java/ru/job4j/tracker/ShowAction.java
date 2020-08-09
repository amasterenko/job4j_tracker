package ru.job4j.tracker;

public class ShowAction implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        Item[] items = tracker.findAll();
        for (Item itm: items) {
            System.out.println(itm);
            System.out.println();
        }
        return true;
    }
}
