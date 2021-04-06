package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionMockitoTest {
    @Test
    public void whenFindExistingItem() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item addedItem = tracker.add(new Item("Item for searching"));
        FindByNameAction fnd = new FindByNameAction(output);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Item for searching");
        fnd.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is("=== Find Items by name ===" + ln
                + addedItem + ln));
    }

    @Test
    public void whenFindNonExistentItem() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        FindByNameAction fnd = new FindByNameAction(output);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Item for searching");
        fnd.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is("=== Find Items by name ===" + ln
                + "No items found." + ln));
    }
}