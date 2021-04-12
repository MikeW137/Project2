package com.bookstore.demo.service;

import com.bookstore.demo.exception.InformationExistException;
import com.bookstore.demo.exception.InformationNotFoundException;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        System.out.println("service calling getBooks ==>");
        return bookRepository.findAll();
    }

    public Book createBook(Book bookObject) {
        System.out.println("service calling createBook ==>");

        Book book = bookRepository.findByTitle(bookObject.getTitle());
        if (book != null) {
            throw new InformationExistException("Book with title " + book.getTitle() + " already exists");
        } else {
            return bookRepository.save(bookObject);
        }
    }
}
