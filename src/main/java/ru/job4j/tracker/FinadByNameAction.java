package ru.job4j.tracker;

public class FinadByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Items by name ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name: ");
        Item[] items = tracker.findByName(name);
        if (items.length != 0) {
            for (Item itm: items) {
                System.out.println(itm);
                System.out.println();
            }
        } else {
            System.out.println("No items found.");
        }
        return true;
    }
}
