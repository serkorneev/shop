package com.griddynamics.devschool.shop.repository;

import com.griddynamics.devschool.shop.entity.Item;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author Sergey Korneev
 */
@Repository("itemRepository")
public class ItemRepository {
    private ArrayList<Item> items = new ArrayList<>();

    public ItemRepository() {
        items.add(createItem("Brand1", 10));
        items.add(createItem("Brand2", 20));
        items.add(createItem("Brand3", 30));
        items.add(createItem("Brand4", 40));
    }

    private Item createItem(String name, int price) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);

        return item;
    }

    public ArrayList<Item> findAll() {
        return this.items;
    }

    public ArrayList<Item> findAll(ArrayList<Item> excludedItems) {
        if (excludedItems == null) {
            return this.findAll();
        }
        ArrayList<Item> includedItems = new ArrayList<>();
        for (Item item: items) {
            if (excludedItems.contains(item)) {
                continue;
            }
            includedItems.add(item);
        }

        return includedItems;
    }

    public Item findByName(String name) throws NotFoundException {
        for (Item item: items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        throw new NotFoundException();
    }
}
