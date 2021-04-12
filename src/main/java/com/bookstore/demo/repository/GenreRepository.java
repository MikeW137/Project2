package com.bookstore.demo.repository;


import com.bookstore.demo.model.Author;
import com.bookstore.demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository  extends JpaRepository<Genre, Long> {
    List<Genre> findByBookId(Long genreId);
}
