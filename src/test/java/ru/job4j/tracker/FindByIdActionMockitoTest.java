package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionMockitoTest {
    @Test
    public void whenFindExistingItem() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Item for searching"));
        FindByIdAction fnd = new FindByIdAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        fnd.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is("=== Find Item by id ===" + ln
                + "{id=1, name='Item for searching'}" + ln));
    }

    @Test
    public void whenFindNonExistentItem() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        FindByIdAction fnd = new FindByIdAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        fnd.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is("=== Find Item by id ===" + ln
                + "Item with id 1 was not found." + ln));
    }
}