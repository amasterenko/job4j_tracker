package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Item with id " + id + " was successfully deleted.");
        } else {
            System.out.println("Item with id " + id + " was not found.");
        }
        return true;
    }
}
