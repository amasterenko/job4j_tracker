package ru.job4j.tracker;

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
    public boolean execute(Input input, Tracker tracker) {
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
