package ru.job4j.tracker.hsqldb;

import org.junit.Test;
import ru.job4j.tracker.store.HbmTracker;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Store;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HbmTrackerTest {

    @Test
    public void whenAddOneItemAndSelectAllThenOneRow() {
        Store store = new HbmTracker();
        Item item = new Item("item1", "desc1", new Timestamp(1620118800000L));
        store.add(item);
        Item foundItem = store.findAll().get(0);
        assertThat(store.findAll().size(), is(1));
        assertThat(foundItem.getId(), is(1));
        assertThat(foundItem.getName(), is("item1"));
        assertThat(foundItem.getDescription(), is("desc1"));
        assertThat(foundItem.getCreated(), is(new Timestamp(1620118800000L)));
    }

    @Test
    public void whenAddOneItemAndReplaceThenOneRow() {
        Store store = new HbmTracker();
        Item item = new Item("item1", "desc1", new Timestamp(1620118800000L));
        item = store.add(item);
        item.setName("item1-Replaced");
        boolean rsl = store.replace(item.getId(), item);
        Item foundItem = store.findAll().get(0);
        assertThat(rsl, is(true));
        assertThat(store.findAll().size(), is(1));
        assertThat(foundItem.getId(), is(1));
        assertThat(foundItem.getName(), is("item1-Replaced"));
        assertThat(foundItem.getDescription(), is("desc1"));
        assertThat(foundItem.getCreated(), is(new Timestamp(1620118800000L)));
    }

    @Test
    public void whenAddOneItemAndReplaceNotExistedThenNoAffects() {
        Store store = new HbmTracker();
        Item item = new Item("item1", "desc1", new Timestamp(1620118800000L));
        item = store.add(item);
        item.setName("item1-Replaced");
        boolean rsl = store.replace(2, item);
        Item foundItem = store.findAll().get(0);
        assertThat(rsl, is(false));
        assertThat(store.findAll().size(), is(1));
        assertThat(foundItem.getId(), is(1));
        assertThat(foundItem.getName(), is("item1"));
        assertThat(foundItem.getDescription(), is("desc1"));
        assertThat(foundItem.getCreated(), is(new Timestamp(1620118800000L)));
    }

    @Test
    public void whenAddOneItemAndDeleteThenNoRows() {
        Store store = new HbmTracker();
        Item item = new Item("item1", "desc1", new Timestamp(1620118800000L));
        item = store.add(item);
        boolean rsl = store.delete(item.getId());
        List<Item> foundItems = store.findAll();
        assertThat(rsl, is(true));
        assertThat(foundItems.size(), is(0));
    }

    @Test
    public void whenAddOneItemAndDeleteNotExistedIdThenNoAffects() {
        Store store = new HbmTracker();
        Item item = new Item("item1", "desc1", new Timestamp(1620118800000L));
        item = store.add(item);
        boolean rsl = store.delete(2);
        List<Item> foundItems = store.findAll();
        assertThat(rsl, is(false));
        assertThat(foundItems.size(), is(1));
        assertThat(foundItems.get(0), is(item));
    }

    @Test
    public void whenAddTwoItemsAndDeleteOneThenOneRow() {
        Store store = new HbmTracker();
        Item item1 = new Item("item1", "desc1", new Timestamp(1620118800000L));
        item1 = store.add(item1);
        Item item2 = new Item("item1", "desc1", new Timestamp(1620118800000L));
        item2 = store.add(item2);
        boolean rsl = store.delete(item2.getId());
        assertThat(rsl, is(true));
        List<Item> foundItems = store.findAll();
        assertThat(foundItems.size(), is(1));
        assertThat(foundItems.get(0), is(item1));
    }

    @Test
    public void whenFindAllThenTwoRows() {
        Store store = new HbmTracker();
        Item item1 = new Item("item1", "desc1", new Timestamp(1620118800000L));
        store.add(item1);
        Item item2 = new Item("item2", "desc2", new Timestamp(1620318800000L));
        store.add(item2);
        List<Item> foundItems = store.findAll();
        assertThat(foundItems.size(), is(2));
        assertThat(foundItems.get(0).getName(), is("item1"));
        assertThat(foundItems.get(1).getName(), is("item2"));
    }

    @Test
    public void whenEmptyStoreAndFindAllThenNoRows() {
        Store store = new HbmTracker();
        List<Item> foundItems = store.findAll();
        assertThat(foundItems.size(), is(0));
    }

    @Test
    public void whenFindByNameThenOneRow() {
        Store store = new HbmTracker();
        Item item1 = new Item("item1", "desc1", new Timestamp(1620118800000L));
        store.add(item1);
        Item item2 = new Item("item2", "desc2", new Timestamp(1620318800000L));
        store.add(item2);
        List<Item> foundItems = store.findByName("item1");
        assertThat(foundItems.size(), is(1));
        assertThat(foundItems.get(0), is(item1));
    }

    @Test
    public void whenFindByNameThenTwoRows() {
        Store store = new HbmTracker();
        Item item1 = new Item("item", "desc1", new Timestamp(1620118800000L));
        store.add(item1);
        Item item2 = new Item("item", "desc2", new Timestamp(1620318800000L));
        store.add(item2);
        List<Item> foundItems = store.findByName("item");
        assertThat(foundItems.size(), is(2));
        assertThat(foundItems, is(List.of(item1, item2)));
    }

    @Test
    public void whenFindByIdThenOneRow() {
        Store store = new HbmTracker();
        Item item1 = new Item("item", "desc", new Timestamp(1620118800000L));
        store.add(item1);
        Item item2 = new Item("item", "desc", new Timestamp(1620118800000L));
        store.add(item2);
        Item foundItem = store.findById(2);
        assertThat(foundItem, is(item2));
    }

}