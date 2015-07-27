package com.griddynamics.devschool.shop.repository;

import com.griddynamics.devschool.shop.entity.Item;
import com.griddynamics.devschool.shop.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

/**
 * @author Sergey Korneev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/repository-config.xml"})
public class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Test
    public void findByName() throws Exception {
        Item item = createItem("SomeName", 10);
        itemRepository.save(item);

        Item foundedItem = itemRepository.findOneByName("SomeName");
        Assert.assertEquals(item.getName(), foundedItem.getName());

        foundedItem = itemRepository.findOneByName("NotExist");
        Assert.assertNull(foundedItem);
    }

    @Test
    public void findAllForBuying() throws Exception {
        Item item = createItem("SomeName", 10);
        itemRepository.save(item);
        Item boughtItem = createItem("BoughtItem", 10);
        itemRepository.save(boughtItem);

        User user = new User();
        user.addItem(boughtItem);
        userRepository.save(user);

        Collection<Item> foundedItems = itemRepository.findAllForBuying(user.getId());
        Assert.assertEquals(1, foundedItems.size());
        Assert.assertEquals(foundedItems.iterator().next().getName(), item.getName());
    }

    private Item createItem(String name, int price) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);

        return item;
    }
}
