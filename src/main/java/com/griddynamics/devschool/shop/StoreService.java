package com.griddynamics.devschool.shop;

import com.griddynamics.devschool.shop.exception.AccessDeniedException;
import com.griddynamics.devschool.shop.exception.NotFoundException;

import java.util.Scanner;

/**
 * @author Sergey Korneev
 */
public class StoreService {
    private Store store;
    private Scanner sc;

    public StoreService(Store store, Scanner sc) {
        this.store = store;
        this.sc = sc;
    }

    public void run() {
        System.out.println("Welcome! Enter \"?\" for help");
        while (true) {
            switch (sc.next()) {
            case "reg":
                System.out.println("Enter your name:");
                String name = sc.next();
                System.out.println("Enter your email:");
                String email = sc.next();

                store.registration(name, email);
                System.out.println("Welcome, " + name + "!");
                break;

            case "list":
                for (Item item: store.getItems()) {
                    System.out.println(item.getName() + " $" + item.getPrice());
                }
                break;

            case "buy":
                System.out.println("Enter item's name:");
                String itemName = sc.next();
                try {
                    store.buy(itemName);
                    System.out.println("Item " + itemName + " was added.");
                } catch (AccessDeniedException e) {
                    System.out.println("Please, register before buying.");
                } catch (NotFoundException e) {
                    System.out.println("Item " + itemName + " not found.");
                }
                break;

            case "exit":
                System.out.println("Goodbye!");
                return;

            case "?":
                System.out.println("Available commands:");
                System.out.println("reg - registration");
                System.out.println("list - show items list");
                System.out.println("buy - buy item");
                System.out.println("exit - leave the shop");
                break;

            default:
                System.out.println("Command not found");
                break;
            }
        }
    }
}
