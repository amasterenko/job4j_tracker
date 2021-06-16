package ru.job4j.tracker.actions;

import ru.job4j.tracker.store.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.output.Output;

public class ReplaceAction implements UserAction {
    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Edit Item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Edit Item ===");
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter new name:");
        Item item = new Item(id, name);
        if (tracker.replace(id, item)) {
            out.println("Item with id " + id + " was edited.");
        } else {
            out.println("Item with id " + id + " was not found.");
        }
        return true;
    }
}
