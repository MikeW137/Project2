package com.bookstore.demo.service;

import com.bookstore.demo.exception.InformationExistException;
import com.bookstore.demo.exception.InformationNotFoundException;
import com.bookstore.demo.model.Author;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.model.Genre;
import com.bookstore.demo.model.Publisher;
import com.bookstore.demo.repository.AuthorRepository;
import com.bookstore.demo.repository.BookRepository;
import com.bookstore.demo.repository.GenreRepository;
import com.bookstore.demo.repository.PublisherRepository;
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
    private GenreRepository genreRepository;
    private PublisherRepository publisherRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) { this.genreRepository = genreRepository;}

    @Autowired
    public void setPublisherRepository(PublisherRepository publisherRepository) { this.publisherRepository = publisherRepository;}


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

    public Book updateBook(Long bookId, Book bookObject) {
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

    public Optional<Book> deleteBook(Long bookId) {
        System.out.println("service calling deleteBook ==>");
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
        System.out.println("service calling createBookAuthor ==>");
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

    public List<Genre> getBookGenres(Long bookId) {
        System.out.println("service calling getBookGenres ==>");
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book.get().getGenreList();
        } else {
            throw new InformationNotFoundException("book with an id " + bookId + " not found");
        }
    }

    public Genre createBookGenre(Long bookId, Genre genreObject) {
        System.out.println("service calling create ==>");
        try {
            Optional book = bookRepository.findById(bookId);
            genreObject.setBook((Book) book.get());
            return genreRepository.save(genreObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("book with id " + bookId + " not found");
        }
    }

    public Genre updateBookGenres(Long bookId, Long genreId, Genre genreObject){
        System.out.println("service calling updateBookGenres ==>");
        try {
            Genre genre = (genreRepository.findByBookId(bookId).stream().filter(p -> p.getId().equals(genreId)).findFirst()).get();
            genre.setName(genreObject.getName());
            return genreRepository.save(genre);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("genre or book not found");
        }
    }

    public void deleteBookGenre(Long bookId, Long genreId) {
        try {
            Genre genre = (genreRepository.findByBookId(bookId).stream().filter(p -> p.getId().equals(genreId)).findFirst()).get();
            genreRepository.deleteById(genre.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("book or genre not found");
        }
    }

    public List<Publisher> getBookPublishers(Long bookId) {
        System.out.println("service calling getBookPublishers ==>");
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book.get().getPublisherList();
        } else {
            throw new InformationNotFoundException("publisher with an id " + bookId + " not found");
        }
    }

    public Publisher createBookPublisher(Long bookId, Publisher publisherObject) {
        System.out.println("service calling createBookPublisher ==>");
        try {
            Optional book = bookRepository.findById(bookId);
            publisherObject.setBook((Book) book.get());
            return publisherRepository.save(publisherObject);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("publisher with id " + bookId + " not found");
        }
    }

    public Publisher updateBookPublisher(Long bookId, Long publisherId, Publisher publisherObject){
        System.out.println("service calling updateBookPublishers ==>");
        try {
            Publisher publisher = (publisherRepository.findByBookId(bookId).stream().filter(p -> p.getId().equals(publisherId)).findFirst()).get();
            publisher.setName(publisherObject.getName());
            publisher.setLocation(publisherObject.getLocation());
            return publisherRepository.save(publisher);
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("publisher or book not found");
        }
    }

    public void deleteBookPublisher(Long bookId, Long publisherId) {
        try {
            Publisher publisher = (publisherRepository.findByBookId(bookId).stream().filter(p -> p.getId().equals(publisherId)).findFirst()).get();
            publisherRepository.deleteById(publisher.getId());
        } catch (NoSuchElementException e) {
            throw new InformationNotFoundException("book or publisher not found");
        }
    }

}



