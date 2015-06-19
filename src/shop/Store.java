package shop;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Sergey Korneev
 */
public class Store {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cmd;
        boolean exit = false;
        Store store = new Store();

        System.out.println("Welcome! Enter \"?\" for help");
        while (!exit) {
            cmd = sc.next();
            switch (cmd) {
            case "reg":
                store.user = new User();
                System.out.println("Enter your name:");
                store.user.setName(sc.next());
                System.out.println("Enter your email:");
                store.user.setEmail(sc.next());
                System.out.println("Welcome, " + store.user.getName() + "!");
                break;

            case "list":
                store.showItems();
                break;

            case "buy":
                System.out.println("Enter item's name:");
                store.buyItemByName(sc.next());
                break;

            case "exit":
                exit = true;
                System.out.println("Goodbye!");
                break;

            case "?":
                store.showHelp();
                break;

            default:
                System.out.println("Command not found");
                break;
            }
        }
    }

    /**
     * @param itemName
     */
    public void buyItemByName(String itemName) {
        if (user == null) {
            System.out.println("Please, register before buying.");

            return;
        }

        for (Item item: items) {
            if (item.getName().equals(itemName)) {
                user.getCart().add(item);
                System.out.println("Item " + itemName + " was added.");
                
                return;
            }
        }

        System.out.println("Item " + itemName + " not found.");
    }

    public void showHelp() {
        System.out.println("Available commands:");
        System.out.println("reg - registration");
        System.out.println("list - show items list");
        System.out.println("buy - buy item");
        System.out.println("exit - leave the shop");
    }

    public void showItems() {
        for (Item item: items) {
            if (user != null && user.getCart().contains(item)) {
                continue;
            }
            System.out.println(item.getName() + " $" + item.getPrice());
        }
    }
}
