package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ItemTest {
    @Test
    public void whenSortItemsByNameAsc() {
        List<Item> list = Arrays.asList(
                new Item(1, "ItemA"),
                new Item(7, "ItemC"),
                new Item(3, "ItemB"));
        List<Item> listExpexted = Arrays.asList(
                new Item(1, "ItemA"),
                new Item(3, "ItemB"),
                new Item(7, "ItemC"));
        list.sort(new SortItemByNameAsc());
        assertThat(list, is(listExpexted));
    }

    @Test
    public void whenSortItemsByNameDesc() {
        List<Item> list = Arrays.asList(
                new Item(1, "ItemA"),
                new Item(7, "ItemB"),
                new Item(3, "ItemC"));
        List<Item> listExpexted = Arrays.asList(
                new Item(3, "ItemC"),
                new Item(7, "ItemB"),
                new Item(1, "ItemA"));
        list.sort(new SortItemByNameDesc());
        assertThat(list, is(listExpexted));
    }

}