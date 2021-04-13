package com.bookstore.demo;

import com.bookstore.demo.model.Author;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.repository.BookRepository;
import com.bookstore.demo.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookstoreApplicationTests {

    private BookRepository bookRepository;
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("Ensure correct handling of zero")
    public void testGetBook(){
        Book book = new Book(1L, "Book One", "Book One Description");
        Book found = bookRepository.findByTitle("Book One");
        assertEquals(book.toString(), found.toString());
    }
    
    public void testGetAuthor(){

    }

}
