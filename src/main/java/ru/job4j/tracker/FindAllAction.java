package ru.job4j.tracker;

import java.util.List;

public class FindAllAction implements UserAction {
    private final Output out;

    public FindAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        List<Item> items = tracker.findAll();
        for (Item itm: items) {
            out.println(itm);
        }
        return true;
    }
}
