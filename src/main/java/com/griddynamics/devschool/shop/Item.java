package com.griddynamics.devschool.shop;

/**
 * @author Sergey Korneev
 */
public class Item {
    private String name;
    private int price;

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
