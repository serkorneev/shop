package com.griddynamics.devschool.shop;

import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;

import java.util.ArrayList;

/**
 * @author Sergey Korneev
 */
public class Store {
    private ArrayList<Item> items = new ArrayList<>();
    private User user;

    public Store() {
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

    public void registration(String name, String email) {
        user = new User();
        user.setName(name);
        user.setEmail(email);
    }

    public void buy(String itemName) throws AccessDeniedException, NotFoundException {
        if (user == null) {
            throw new AccessDeniedException();
        }

        for (Item item: getItems()) {
            if (item.getName().equals(itemName)) {
                user.getCart().add(item);
                return;
            }
        }

        throw new NotFoundException();
    }

    public ArrayList<Item> getItems() {
        if (user == null) {
            return items;
        }

        ArrayList<Item> notBoughtItems = new ArrayList<>();
        for (Item item: items) {
            if (user.getCart().contains(item)) {
                continue;
            }
            notBoughtItems.add(item);
        }

        return notBoughtItems;
    }
}
