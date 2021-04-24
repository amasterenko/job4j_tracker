package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FindByNameActionTest {
    @Test
    public void whenItemExists() {
        Input in = new StubInput(new String[] {"item2"});
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        FindByNameAction find = new FindByNameAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(
                "=== Find Items by name ===" + System.lineSeparator()
                        + "{id=2, name='item2', desc='', created=''}"
                        + System.lineSeparator()
        ));
    }

    @Test
    public void whenItemsExist() {
        Input in = new StubInput(new String[] {"item2"});
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        tracker.add(new Item("item2"));
        FindByNameAction find = new FindByNameAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(
                "=== Find Items by name ===" + System.lineSeparator()
                        + "{id=2, name='item2', desc='', created=''}"
                        + System.lineSeparator()
                        + "{id=3, name='item2', desc='', created=''}"
                        + System.lineSeparator()
        ));
    }

    @Test
    public void whenNoItemsFound() {
        Input in = new StubInput(new String[] {"item3"});
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        FindByNameAction find = new FindByNameAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(
                "=== Find Items by name ===" + System.lineSeparator()
                        + "No items found." + System.lineSeparator()));
    }

    @Test
    public void whenNoItems() {
        Input in = new StubInput(new String[] {"item1"});
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        FindByNameAction find = new FindByNameAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(
                "=== Find Items by name ===" + System.lineSeparator()
                        + "No items found." + System.lineSeparator()));
    }
}