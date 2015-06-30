package com.griddynamics.devschool.shop;

import java.util.ArrayList;

/**
 * @author Sergey Korneev
 */
public class User {
    private String name;
    private String email;
    private ArrayList<Item> cart = new ArrayList<>();

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
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return ArrayList<Item>
     */
    public ArrayList<Item> getCart() {
        return cart;
    }
}