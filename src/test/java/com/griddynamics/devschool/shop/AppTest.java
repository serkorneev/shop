package com.griddynamics.devschool.shop;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author Sergey Korneev
 */
public class AppTest extends AbstractConsoleTest {
    @Test
    public void testMain() throws Exception {
        mockSystemIn("exit");
        mockSystemOut();

        App.main(new String[] {});

        String[] expected = {
                "Welcome! Enter \"?\" for help",
                "Goodbye!"
        };
        assertArrayEquals(expected, getSystemOutContent());
    }
}
