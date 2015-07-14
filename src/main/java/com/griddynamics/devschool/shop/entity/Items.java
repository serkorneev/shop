package com.griddynamics.devschool.shop.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

/**
 * @author Sergey Korneev
 */
@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.NONE)
public class Items {
    private ArrayList<Item> items;

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @XmlElement(name = "item", type = Item.class)
    public ArrayList<Item> getItems() {
        return items;
    }
}
