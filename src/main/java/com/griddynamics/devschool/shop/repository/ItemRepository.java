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
    @Query(value = "SELECT i.* FROM item i LEFT JOIN user_item ui ON ui.item_id = i.id AND ui.user_id = ?1 WHERE ui.user_id IS NULL", nativeQuery = true)
    public Collection<Item> findAllForBuying(Integer userId);
}
