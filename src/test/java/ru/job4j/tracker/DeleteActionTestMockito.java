package ru.job4j.tracker;

import org.hamcrest.Matchers;
import org.junit.Test;
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
        tracker.add(new Item("Item for deleting"));
        DeleteAction del = new DeleteAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is("=== Delete item ===" + ln
                + "Item with id 1 was successfully deleted." + ln));
        assertThat(tracker.findById(1), Matchers.nullValue());
    }

    @Test
    public void whenDeleteNonExistentItem() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        DeleteAction del = new DeleteAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        del.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is("=== Delete item ===" + ln
                + "Item with id 1 was not found." + ln));
    }
}