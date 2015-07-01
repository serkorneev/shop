package com.griddynamics.devschool.shop;

import java.util.Scanner;

/**
 * @author Sergey Korneev
 */
public class App {
    public static void main(String[] args) {
        StoreService service = new StoreService(new Store(), new Scanner(System.in));
        service.run();
    }
}
