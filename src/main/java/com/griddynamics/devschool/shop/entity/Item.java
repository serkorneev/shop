package com.griddynamics.devschool.shop.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 * @author Sergey Korneev
 */
@Entity
@Table(name = "item")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Item {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "id")
    private Integer id;

    @Column
    @XmlElement(name = "name")
    private String name;

    @Column
    @XmlElement(name = "price")
    private int price;

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return this.name + "-$" + this.price;
    }
}
