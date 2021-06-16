package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.actions.FindAllAction;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;
import ru.job4j.tracker.store.MemTracker;
import ru.job4j.tracker.store.Store;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FindAllActionTest {

    @Test
    public void whenItemExists() {
        Input in = new StubInput(new String[] {""});
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        FindAllAction find = new FindAllAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(
                "{id=1, name='item1', desc='', created=''}"
                        + System.lineSeparator()
                        + "{id=2, name='item2', desc='', created=''}"
                        + System.lineSeparator()
        ));
    }

    @Test
    public void whenNoItems() {
        Input in = new StubInput(new String[] {""});
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        FindAllAction find = new FindAllAction(out);
        find.execute(in, tracker);
        assertThat(out.toString(), is(""));
    }
}