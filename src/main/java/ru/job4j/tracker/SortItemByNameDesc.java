package ru.job4j.tracker;

import ru.job4j.tracker.model.Item;

import java.util.Comparator;

public class SortItemByNameDesc implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        return item2.getName().compareTo(item1.getName());
    }
}
