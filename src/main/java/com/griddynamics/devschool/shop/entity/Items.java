package com.griddynamics.devschool.shop.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Sergey Korneev
 */
@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.NONE)
public class Items {
    private Collection<Item> items;

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    @XmlElement(name = "item", type = Item.class)
    public Collection<Item> getItems() {
        return items;
    }
}
