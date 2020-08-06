package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        return Arrays.copyOf(items, size);
    }

    Item[] findByName(String key) {
        Item[] itemsByName = new Item[size];
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].getName().equals(key)) {
                itemsByName[cnt++] = items[i];
            }
        }
        return Arrays.copyOf(itemsByName, cnt);
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
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
            items[indx] = item;
        }
        return rsl;
    }

    public boolean delete(int id) {
        int indx = indexOf(id);
        boolean rsl = indx != -1;
        if (rsl) {
            System.arraycopy(items, indx + 1, items, indx, size - indx);
            items[size - 1] = null;
            size--;
        }
        return rsl;
    }
}