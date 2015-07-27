package com.griddynamics.devschool.shop;

import com.griddynamics.devschool.shop.entity.Item;
import com.griddynamics.devschool.shop.entity.User;
import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import com.griddynamics.devschool.shop.repository.ItemRepository;
import com.griddynamics.devschool.shop.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.HashSet;

import static org.mockito.Mockito.*;

/**
 * @author Sergey Korneev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context/store-config.xml", "/context/mock-repository-config.xml"})
public class StoreTest {
    @Autowired
    private Store store;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        reset(userRepository);
        reset(itemRepository);
    }

    @Test(expected = AccessDeniedException.class)
    public void buyWithoutRegistration() throws Exception {
        store.buy("Some item name", null);
    }

    @Test
    public void buy() throws Exception {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        when(userRepository.findOne(1)).thenReturn(user);
        Item item = new Item();
        when(itemRepository.findOneByName("ItemName")).thenReturn(item);

        store.buy("ItemName", user);

        verify(userRepository).findOne(1);
        verify(userRepository).save(user);
        verify(itemRepository).findOneByName("ItemName");
        verify(user).addItem(item);
    }

    @Test(expected = NotFoundException.class)
    public void buyNotExistProduct() throws Exception {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        when(userRepository.findOne(1)).thenReturn(user);
        when(itemRepository.findOneByName("ItemName")).thenReturn(null);

        store.buy("ItemName", user);

        verify(userRepository).findOne(1);
        verify(userRepository).save(user);
        verify(itemRepository).findOneByName("ItemName");
    }

    @Test(expected = NotFoundException.class)
    public void buyBoughtItem() throws Exception {
        User user = mock(User.class);
        Item item = new Item();
        Collection<Item> items = new HashSet<>();
        items.add(item);
        when(user.getId()).thenReturn(1);
        when(user.getItems()).thenReturn(items);
        when(userRepository.findOne(1)).thenReturn(user);
        when(itemRepository.findOneByName("ItemName")).thenReturn(item);

        store.buy("ItemName", user);

        verify(userRepository).findOne(1);
        verify(itemRepository).findOneByName("ItemName");
    }

    @Test
    public void getItemsWithoutUser() throws Exception {
        store.getItems(null);

        verify(itemRepository).findAll();
        verify(itemRepository, never()).findAllForBuying(anyInt());
    }

    @Test
    public void getItemsWithUser() throws Exception {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);

        store.getItems(user);

        verify(user).getId();
        verify(itemRepository).findAllForBuying(1);
        verify(itemRepository, never()).findAll();
    }

    @Test
    public void registrationWithExistedUser() throws Exception {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("some email");
        when(userRepository.findOneByEmail("some email")).thenReturn(user);

        store.registration(user);

        verify(user).getEmail();
        verify(userRepository).findOneByEmail("some email");
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void registrationWithNewUser() throws Exception {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("some email");
        when(userRepository.findOneByEmail("some email")).thenReturn(null);

        store.registration(user);

        verify(user).getEmail();
        verify(userRepository).findOneByEmail("some email");
        verify(userRepository).save(user);
    }
}
