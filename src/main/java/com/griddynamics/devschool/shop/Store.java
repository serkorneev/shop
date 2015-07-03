package com.griddynamics.devschool.shop;

import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @author Sergey Korneev
 */
public class Store {
    private static final Logger logger = LoggerFactory.getLogger(Store.class);
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
        logger.debug("Register user with name \"{}\" and email \"{}\"", name, email);
        user = new User();
        user.setName(name);
        user.setEmail(email);
    }

    public void buy(String itemName) throws AccessDeniedException, NotFoundException {
        logger.info("Start buying item {}", itemName);
        if (user == null) {
            logger.warn("Unregistered user tries to buy {}", itemName);
            throw new AccessDeniedException();
        }

        for (Item item: getItems()) {
            if (item.getName().equals(itemName)) {
                user.getCart().add(item);
                logger.info("User {} bought {}.", user.getName(), item.getName());
                return;
            }
        }

        logger.info("Item {} not found.", itemName);
        throw new NotFoundException();
    }

    public ArrayList<Item> getItems() {
        if (user == null) {
            return items;
        }

        logger.debug("User bought {}", user.getCart());
        ArrayList<Item> notBoughtItems = new ArrayList<>();
        for (Item item: items) {
            if (user.getCart().contains(item)) {
                continue;
            }
            notBoughtItems.add(item);
        }

        logger.debug("User can buy {}", notBoughtItems);

        return notBoughtItems;
    }
}
