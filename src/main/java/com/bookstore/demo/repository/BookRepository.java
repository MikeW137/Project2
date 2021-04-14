package com.bookstore.demo.repository;

import com.bookstore.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String titleName);
    List<Book> findByUserId(Long userId);
    //find by user id and return the category
    Book findByIdAndUserId(Long bookId, Long userId);
    // find user by id and by category name
   // Book findByUserIdAndName(Long userId, String categoryName);
}
