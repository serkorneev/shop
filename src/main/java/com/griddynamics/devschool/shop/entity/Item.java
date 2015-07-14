package com.griddynamics.devschool.shop.entity;

import javax.xml.bind.annotation.*;

/**
 * @author Sergey Korneev
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Item {
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "price")
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

    public String toString() {
        return this.name + "-$" + this.price;
    }

    public boolean equals(Object obj) {
        return obj instanceof Item && ((Item) obj).getName().equals(this.getName());
    }
}
