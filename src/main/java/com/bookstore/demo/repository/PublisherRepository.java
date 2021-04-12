package com.bookstore.demo.repository;

import com.bookstore.demo.model.Genre;
import com.bookstore.demo.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findByBookId(Long publisherId);
}
