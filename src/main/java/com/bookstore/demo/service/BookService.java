package com.bookstore.demo.service;

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

//    public Optional getBook(Long bookId) {
//        System.out.println("service calling getBook ==>");
//        Optional book = bookRepository.findById(bookId);
//        if (book.isPresent()) {
//            return book;
//        } else {
//            throw new InformationNotFoundException("Book with id " + bookId + " not found");
//        }
//    }
}
