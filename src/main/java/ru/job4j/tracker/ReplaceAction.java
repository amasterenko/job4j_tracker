package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit Item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter new name:");
        Item item = new Item(id, name);
        if (tracker.replace(id, item)) {
            System.out.println("Item with id " + id + " was edited.");
        } else {
            System.out.println("Item with id " + id + " was not found.");
        }
        return true;
    }
}
