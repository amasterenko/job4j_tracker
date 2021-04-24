package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FindByIdActionTest {
    @Test
    public void whenItemExists() {
        Input in = new StubInput(new String[] {"2"});
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        FindByIdAction find = new FindByIdAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(
                "=== Find Item by id ===" + System.lineSeparator()
                        + "{id=2, name='item2', desc='', created=''}"
                        + System.lineSeparator()
        ));
    }

    @Test
    public void whenNoItemsFound() {
        Input in = new StubInput(new String[] {"3"});
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        FindByIdAction find = new FindByIdAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(
                "=== Find Item by id ===" + System.lineSeparator()
                        + "Item with id 3 was not found." + System.lineSeparator()));
    }

    @Test
    public void whenNoItems() {
        Input in = new StubInput(new String[] {"1"});
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        FindByIdAction find = new FindByIdAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(
                "=== Find Item by id ===" + System.lineSeparator()
                    + "Item with id 1 was not found." + System.lineSeparator()));
    }
}