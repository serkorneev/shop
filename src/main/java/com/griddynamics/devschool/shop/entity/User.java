package com.griddynamics.devschool.shop.entity;

import javax.persistence.*;
import javax.ws.rs.FormParam;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sergey Korneev
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @FormParam("name")
    private String name;

    @FormParam("email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_item",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items = new HashSet<>();

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Collection<Item> getItems() {
        return Collections.unmodifiableSet(items);
    }
}
