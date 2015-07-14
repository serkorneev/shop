package com.griddynamics.devschool.shop;

import com.griddynamics.devschool.shop.entity.Item;
import com.griddynamics.devschool.shop.entity.User;
import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import com.griddynamics.devschool.shop.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * @author Sergey Korneev
 */
public class StoreTest {
    private Store store;
    private ItemRepository repository;

    @Before
    public void setUp() {
        repository = mock(ItemRepository.class);
        store = new Store();
        store.setRepository(repository);
    }

    @Test(expected = AccessDeniedException.class)
    public void buyWithoutRegistration() throws Exception {
        store.buy("Some item name", null);
    }

    @Test
    public void buy() throws Exception {
        when(repository.findByName("ItemName")).thenReturn(new Item());
        store.buy("ItemName", new User());
        verify(repository).findByName("ItemName");
    }

    @Test(expected = NotFoundException.class)
    public void buyBoughtItem() throws Exception {
        Item item = new Item();
        item.setName("ItemName");
        User user = new User();
        user.getCart().add(item);
        when(repository.findByName("ItemName")).thenReturn(item);
        store.buy("ItemName", user);
    }

    @Test
    public void getItems() throws Exception {
        store.getItems(null);
        verify(repository).findAll(null);

        User user = new User();
        store.getItems(user);
        verify(repository).findAll(user.getCart());
    }
}
