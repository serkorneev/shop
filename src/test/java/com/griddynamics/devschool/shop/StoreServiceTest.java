package com.griddynamics.devschool.shop;

import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * @author Sergey Korneev
 */
public class StoreServiceTest extends AbstractConsoleTest {

    @Test
    public void testRunWithHelpCommand() throws Exception {
        mockSystemIn("? exit");
        mockSystemOut();
        Store store = mock(Store.class);

        StoreService service = new StoreService(store, new Scanner(System.in));
        service.run();

        String[] expected = {
                "Welcome! Enter \"?\" for help",
                "Available commands:",
                "reg - registration",
                "list - show items list",
                "buy - buy item",
                "exit - leave the shop",
                "Goodbye!"
        };
        assertArrayEquals(expected, getSystemOutContent());
    }

    @Test
    public void testRunWithNotExistCommand() throws Exception {
        mockSystemIn("not-exist-command exit");
        mockSystemOut();
        Store store = mock(Store.class);

        StoreService service = new StoreService(store, new Scanner(System.in));
        service.run();

        String[] expected = {
                "Welcome! Enter \"?\" for help",
                "Command not found",
                "Goodbye!"
        };
        assertArrayEquals(expected, getSystemOutContent());
    }

    @Test
    public void testRunWithExitCommand() throws Exception {
        mockSystemIn("exit");
        mockSystemOut();
        Store store = mock(Store.class);

        StoreService service = new StoreService(store, new Scanner(System.in));
        service.run();

        String[] expected = {
                "Welcome! Enter \"?\" for help",
                "Goodbye!"
        };
        assertArrayEquals(expected, getSystemOutContent());
    }

    @Test
    public void testRunWithRegCommand() throws Exception {
        mockSystemIn("reg TestName TestEmail exit");
        mockSystemOut();
        Store store = mock(Store.class);

        StoreService service = new StoreService(store, new Scanner(System.in));
        service.run();

        String[] expected = {
                "Welcome! Enter \"?\" for help",
                "Enter your name:",
                "Enter your email:",
                "Welcome, TestName!",
                "Goodbye!"
        };
        assertArrayEquals(expected, getSystemOutContent());
        verify(store).registration("TestName", "TestEmail");
    }

    @Test
    public void testRunWithListCommand() throws Exception {
        mockSystemIn("list exit");
        mockSystemOut();
        Store store = mock(Store.class);

        Item item = new Item();
        item.setName("TestItem");
        item.setPrice(1);
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        when(store.getItems()).thenReturn(items);

        StoreService service = new StoreService(store, new Scanner(System.in));
        service.run();

        String[] expected = {
                "Welcome! Enter \"?\" for help",
                "TestItem $1.0",
                "Goodbye!"
        };
        assertArrayEquals(expected, getSystemOutContent());
        verify(store).getItems();
    }

    @Test
    public void testRunWithBuyCommand() throws Exception {
        mockSystemIn("buy SomeItem exit");
        mockSystemOut();
        Store store = mock(Store.class);

        StoreService service = new StoreService(store, new Scanner(System.in));
        service.run();

        String[] expected = {
                "Welcome! Enter \"?\" for help",
                "Enter item's name:",
                "Item SomeItem was added.",
                "Goodbye!"
        };
        assertArrayEquals(expected, getSystemOutContent());
        verify(store).buy("SomeItem");
    }

    @Test
    public void testRunWithBuyCommandWithAccessDeniedException() throws Exception {
        mockSystemIn("buy SomeItem exit");
        mockSystemOut();
        Store store = mock(Store.class);
        doThrow(new AccessDeniedException()).when(store).buy("SomeItem");

        StoreService service = new StoreService(store, new Scanner(System.in));
        service.run();

        String[] expected = {
                "Welcome! Enter \"?\" for help",
                "Enter item's name:",
                "Please, register before buying.",
                "Goodbye!"
        };
        assertArrayEquals(expected, getSystemOutContent());
        verify(store).buy("SomeItem");
    }

    @Test
    public void testRunWithBuyCommandWithNotFoundException() throws Exception {
        mockSystemIn("buy SomeItem exit");
        mockSystemOut();
        Store store = mock(Store.class);
        doThrow(new NotFoundException()).when(store).buy("SomeItem");

        StoreService service = new StoreService(store, new Scanner(System.in));
        service.run();

        String[] expected = {
                "Welcome! Enter \"?\" for help",
                "Enter item's name:",
                "Item SomeItem not found.",
                "Goodbye!"
        };
        assertArrayEquals(expected, getSystemOutContent());
        verify(store).buy("SomeItem");
    }
}
