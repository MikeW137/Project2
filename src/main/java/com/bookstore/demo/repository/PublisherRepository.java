package com.bookstore.demo.repository;

import com.bookstore.demo.model.Genre;
import com.bookstore.demo.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findByBookId(Long publisherId);
}
