package com.griddynamics.devschool.shop.repository;

import com.griddynamics.devschool.shop.entity.Item;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergey Korneev
 */
public class ItemRepositoryTest {
    private ItemRepository repository;

    @Before
    public void setUp() {
        repository = new ItemRepository();
    }

    @Test
    public void findAll() throws Exception {
        ArrayList<Item> items = repository.findAll();

        assertEquals(4, items.size());
        assertItem("Brand1", 10, items.get(0));
        assertItem("Brand2", 20, items.get(1));
        assertItem("Brand3", 30, items.get(2));
        assertItem("Brand4", 40, items.get(3));
    }

    @Test
    public void findAllWithNullArgument() throws Exception {
        ArrayList<Item> items = repository.findAll(null);

        assertEquals(4, items.size());
        assertItem("Brand1", 10, items.get(0));
        assertItem("Brand2", 20, items.get(1));
        assertItem("Brand3", 30, items.get(2));
        assertItem("Brand4", 40, items.get(3));
    }

    @Test
    public void findAllWithExcludedItems() throws Exception {
        Item item = new Item();
        item.setName("Brand1");
        ArrayList<Item> excludedItems = new ArrayList<>();
        excludedItems.add(item);

        ArrayList<Item> items = repository.findAll(excludedItems);

        assertEquals(3, items.size());
        assertItem("Brand2", 20, items.get(0));
        assertItem("Brand3", 30, items.get(1));
        assertItem("Brand4", 40, items.get(2));
    }

    @Test
    public void findByName() throws Exception {
        Item item = repository.findByName("Brand1");

        assertItem("Brand1", 10, item);
    }

    @Test(expected = NotFoundException.class)
    public void findByNameWithException() throws Exception {
        repository.findByName("ItemName not exist");
    }

    private void assertItem(String name, double price, Item item) {
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice(), 0);
    }
}
