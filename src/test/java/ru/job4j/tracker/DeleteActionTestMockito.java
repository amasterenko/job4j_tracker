package ru.job4j.tracker;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.job4j.tracker.actions.DeleteAction;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;
import ru.job4j.tracker.store.MemTracker;
import ru.job4j.tracker.store.Store;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTestMockito {

    @Test
    public void whenDeleteExistingItem() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item addedItem = tracker.add(new Item("Item for deleting"));
        DeleteAction del = new DeleteAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is("=== Delete item ===" + ln
                + "Item with id " + addedItem.getId() + " was successfully deleted." + ln));
        assertThat(tracker.findById(1), Matchers.nullValue());
    }

    @Test
    public void whenDeleteNonExistentItem() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        DeleteAction del = new DeleteAction(output);
        Input input = mock(Input.class);
        int id = 1;
        when(input.askInt(any(String.class))).thenReturn(id);
        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is("=== Delete item ===" + ln
                + "Item with id " + id + " was not found." + ln));
    }
}