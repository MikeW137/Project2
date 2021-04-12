package com.bookstore.demo.repository;

import org.springframework.stereotype.Repository;



@Repository
// JpaRepository<TABLE_NAME, PK>
public interface BookRepository extends JpaRepository<Book, Long> {

}
