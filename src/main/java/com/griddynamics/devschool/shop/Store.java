package com.griddynamics.devschool.shop;

import com.griddynamics.devschool.shop.entity.Item;
import com.griddynamics.devschool.shop.entity.User;
import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import com.griddynamics.devschool.shop.repository.ItemRepository;
import com.griddynamics.devschool.shop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * @author Sergey Korneev
 */
@Service
public class Store {
    private static final Logger logger = LoggerFactory.getLogger(Store.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User registration(User user) {
        User foundedUser = userRepository.findOneByEmail(user.getEmail());
        if (foundedUser == null) {
            return userRepository.save(user);
        }

        return foundedUser;
    }

    @Transactional
    public void buy(String itemName, User user) throws AccessDeniedException, NotFoundException {
        logger.info("Start buying item {}", itemName);
        if (user == null) {
            logger.warn("Unregistered user tries to buy {}", itemName);
            throw new AccessDeniedException();
        }

        user = userRepository.findOne(user.getId());
        Item item = itemRepository.findOneByName(itemName);
        if (item != null && !user.getItems().contains(item)) {
            user.addItem(item);
            userRepository.save(user);
            logger.info("User {} bought {}.", user.getName(), item.getName());
            return;
        }

        throw new NotFoundException();
    }

    public Collection<Item> getItems(User user) {
        if (user == null) {
            return (Collection<Item>) itemRepository.findAll();
        }
        return itemRepository.findAllForBuying(user.getId());
    }
}
