package com.bookstore.demo.service;

import com.bookstore.demo.exception.InformationExistException;
import com.bookstore.demo.exception.InformationNotFoundException;
import com.bookstore.demo.model.Author;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.repository.AuthorRepository;
import com.bookstore.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {
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

    public Book updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book bookObject) {
        System.out.println("service calling updateBook ==>");
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            if (bookObject.getTitle().equals(book.get().getTitle())) {
                System.out.println("Same");
                throw new InformationExistException("book " + book.get().getTitle() + " is already exists");
            } else {
                Book updateBook = bookRepository.findById(bookId).get();
                updateBook.setTitle(bookObject.getTitle());
                updateBook.setDescription(bookObject.getDescription());
                return bookRepository.save(updateBook);
            }
        } else {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }

    public Optional<Book> deleteBook(@PathVariable(value = "bookId") Long bookId) {
        System.out.println("service calling deleteCategory ==>");
        Optional<Book> book = bookRepository.findById(bookId);

        if (book.isPresent()) {
            bookRepository.deleteById(bookId);
            return book;
        } else {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }

    public List<Author> getBookAuthors(Long bookId) {
        System.out.println("service calling getBookAuthors ==>");
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book.get().getAuthorList();
        } else {
            throw new InformationNotFoundException("book with an id " + bookId + " not found");
        }
    }

    public Author createBookAuthor(Long bookId, Author authorObject) {
        System.out.println("service calling createCategoryRecipe ==>");
        try {
            Optional book = bookRepository.findById(bookId);
            authorObject.setBook((Book) book.get());
            return authorRepository.save(authorObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }

    public Author updateBookAuthor(Long bookId, Long authorId, Author authorObject) {
        System.out.println("service calling updateBookAuthor ==>");
        try {
            Author author = (authorRepository.findByBookId(bookId).stream().filter(p -> p.getId().equals(authorId)).findFirst()).get();
            author.setName(authorObject.getName());
            author.setNationality(authorObject.getNationality());
            author.setAge(authorObject.getAge());
            return authorRepository.save(author);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("author or book not found");
        }
    }

    public void deleteBookAuthor(Long bookId, Long authorId) {
        try {
            Author author = (authorRepository.findByBookId(bookId).stream().filter(p -> p.getId().equals(authorId)).findFirst()).get();
            authorRepository.deleteById(author.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("book or author not found");
        }
    }

    public List<Author> getBookGenres(Long bookId) {
        System.out.println("service calling getBookGenres ==>");
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book.get().getAuthorList();
        } else {
            throw new InformationNotFoundException("book with an id " + bookId + " not found");
        }
    }
}
