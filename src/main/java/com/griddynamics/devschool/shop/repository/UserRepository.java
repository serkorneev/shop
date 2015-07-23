package com.griddynamics.devschool.shop.repository;

import com.griddynamics.devschool.shop.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Sergey Korneev
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findOneByEmail(String email);
}
