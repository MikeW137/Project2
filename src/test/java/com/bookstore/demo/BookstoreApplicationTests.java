package com.bookstore.demo;

import com.bookstore.demo.model.Author;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.repository.AuthorRepository;
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
    private AuthorRepository authorRepository;
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    void contextLoads() {
    }

    @Test

    public void testGetBook(){
        Book book = new Book(1L, "Book One", "Book One Description");
        Book found = bookRepository.findByTitle("Book One");
        assertEquals(book.toString(), found.toString());
    }

    @Test
    public void testGetAuthor(){
        Author author = new Author(2L, "Marc", "USA", 32, new Book(1L, "Book One", "Book One Description"));
        List<Author> found = authorRepository.findByBookId(1L);
        assertEquals(author.toString(), found.get(0).toString());
    }

}
