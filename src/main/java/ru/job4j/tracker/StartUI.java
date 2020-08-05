package ru.job4j.tracker;

import java.util.Arrays;

public class StartUI {
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Item first = new Item(1, "First item");
        tracker.add(first);
        Item found = tracker.findById(1);
        if (found != null) {
            System.out.println(found.getId() + " - " + found.getName());
        }
    }
}
