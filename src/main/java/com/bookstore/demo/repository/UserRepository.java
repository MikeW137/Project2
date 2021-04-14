package com.bookstore.demo.repository;

import com.bookstore.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAddress(String userEmailAddress);

    User findByEmailAddress(String userEmailAddress);
}
