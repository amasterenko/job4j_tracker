package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FindAllActionTest {

    @Test
    public void whenItemExists() {
        Input in = new StubInput(new String[] {""});
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        FindAllAction find = new FindAllAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(
                "{id=1, name='item1'}" + System.lineSeparator()
                        + "{id=2, name='item2'}" + System.lineSeparator()
        ));
    }

    @Test
    public void whenNoItems() {
        Input in = new StubInput(new String[] {""});
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        FindAllAction find = new FindAllAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(""));
    }
}