package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;
    //private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public List<Item> findAll() {
        return items;
    }

    List<Item> findByName(String key) {
        List<Item> itemsByName = new ArrayList<>();
        int cnt = 0;
        for (Item item : items) {
            if (item.getName().equals(key)) {
                itemsByName.add(item);
            }
        }
        return itemsByName;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        int indx = indexOf(id);
        boolean rsl = indx != -1;
        if (rsl) {
            item.setId(id);
            items.set(indx, item);
        }
        return rsl;
    }

    public boolean delete(int id) {
        int indx = indexOf(id);
        boolean rsl = indx != -1;
        if (rsl) {
            items.remove(indx);
        }
        return rsl;
    }
}