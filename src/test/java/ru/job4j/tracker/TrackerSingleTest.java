package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.store.TrackerSingleClass;
import ru.job4j.tracker.store.TrackerSingleEnum;
import ru.job4j.tracker.store.TrackerSingleFinalField;
import ru.job4j.tracker.store.TrackerSingleStaticField;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class TrackerSingleTest {
    @Test
    public void whenSingleEnum2Instances() {
        TrackerSingleEnum tracker1 = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
        assertThat(tracker1.equals(tracker2), is(true));
    }

    @Test
    public void whenSingleStaticField2Instances() {
        TrackerSingleStaticField tracker1 = TrackerSingleStaticField.getInstance();
        TrackerSingleStaticField tracker2 = TrackerSingleStaticField.getInstance();
        assertThat(tracker1.equals(tracker2), is(true));
    }

    @Test
    public void whenSingleFinalField2Instances() {
        TrackerSingleFinalField tracker1 = TrackerSingleFinalField.getInstance();
        TrackerSingleFinalField tracker2 = TrackerSingleFinalField.getInstance();
        assertThat(tracker1.equals(tracker2), is(true));
    }

    @Test
    public void whenSingleClass2Instances() {
        TrackerSingleClass tracker1 = TrackerSingleClass.getInstance();
        TrackerSingleClass tracker2 = TrackerSingleClass.getInstance();
        assertThat(tracker1.equals(tracker2), is(true));
    }
}