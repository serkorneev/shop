package com.griddynamics.devschool.shop;

import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Sergey Korneev
 */
public class StoreTest {
    @Test(expected = AccessDeniedException.class)
    public void testBuyWithoutRegistration() throws Exception {
        Store store = new Store(null);
        store.buy("Some item name");
    }

    @Test(expected = NotFoundException.class)
    public void testBuyNotExistItem() throws Exception {
        Store store = new Store(new User());
        store.buy("ItemName not exist");
    }

    @Test
    public void testGetItems() throws Exception {
        Store store = new Store(null);
        ArrayList<Item> items = store.getItems();

        assertEquals(4, items.size());
        assertItem("Brand1", 10, items.get(0));
        assertItem("Brand2", 20, items.get(1));
        assertItem("Brand3", 30, items.get(2));
        assertItem("Brand4", 40, items.get(3));
    }

    @Test
    public void testGetItemsWithoutBought() throws Exception {
        Store store = new Store(new User());
        store.buy("Brand1");
        ArrayList<Item> items = store.getItems();

        assertEquals(3, items.size());
        assertItem("Brand2", 20, items.get(0));
        assertItem("Brand3", 30, items.get(1));
        assertItem("Brand4", 40, items.get(2));
    }

    private void assertItem(String name, double price, Item item) {
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice(), 0);
    }
}
