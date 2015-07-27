package com.griddynamics.devschool.shop.repository;

import com.griddynamics.devschool.shop.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * @author Sergey Korneev
 */
public interface ItemRepository extends CrudRepository<Item, Integer> {
    public Item findOneByName(String name);
    @Query("SELECT i FROM Item i LEFT JOIN i.users u ON u.id = ?1 WHERE u.id IS NULL")
    public Collection<Item> findAllForBuying(Integer userId);
}
