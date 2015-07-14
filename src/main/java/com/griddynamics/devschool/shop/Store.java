package com.griddynamics.devschool.shop;

import com.griddynamics.devschool.shop.entity.Item;
import com.griddynamics.devschool.shop.entity.User;
import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import com.griddynamics.devschool.shop.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Sergey Korneev
 */
@Service("store")
public class Store {
    private static final Logger logger = LoggerFactory.getLogger(Store.class);
    private ItemRepository repository;

    @Autowired
    public void setRepository(ItemRepository repository) {
        this.repository = repository;
    }

    public void buy(String itemName, User user) throws AccessDeniedException, NotFoundException {
        logger.info("Start buying item {}", itemName);
        if (user == null) {
            logger.warn("Unregistered user tries to buy {}", itemName);
            throw new AccessDeniedException();
        }

        Item item = repository.findByName(itemName);
        if (!user.getCart().contains(item)) {
            user.getCart().add(item);
            logger.info("User {} bought {}.", user.getName(), item.getName());
            return;
        }

        throw new NotFoundException();
    }

    public ArrayList<Item> getItems(User user) {
        return repository.findAll(user == null ? null : user.getCart());
    }
}
