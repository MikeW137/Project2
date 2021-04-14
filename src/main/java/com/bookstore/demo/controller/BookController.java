package com.bookstore.demo.controller;

import com.bookstore.demo.exception.InformationNotFoundException;
import com.bookstore.demo.model.Author;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.model.Genre;
import com.bookstore.demo.model.Publisher;
import com.bookstore.demo.repository.BookRepository;
import com.bookstore.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class BookController {
    private BookService bookService;
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    //Endpoints
    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello world";
    }

    //Get all books
    // http://localhost:9090/api/books/
    @GetMapping("/books")
    public List<Book> getBooks() {
        System.out.println("calling getBooks ==>");
        return bookService.getBooks();
    }

    @GetMapping(path = "/books/{bookId}")
    public Book getBook(@PathVariable Long bookId) {
        System.out.println("calling getCategory ==>");
        return bookService.getBook(bookId);
    }

    //Create a book
    // http://localhost:9090/api/books/
    @PostMapping(path = "/books")
    public ResponseEntity<HashMap> createBook(@RequestBody Book bookObject) {
        System.out.println("calling createBook ==>");
        bookService.createBook(bookObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "book with id: " + bookObject.getId() + " was successfully added.");
        responseMessage.put("result", bookObject);
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);

    }
    //Updating a book
    // http://localhost:9090/api/books/1
    @PutMapping("/books/{bookId}")
    public ResponseEntity<HashMap> updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book bookObject) {
        System.out.println("calling updateBook ==>");
        bookService.updateBook(bookId, bookObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "book with id: " + bookObject.getId() + " was successfully updated.");
        responseMessage.put("result", bookObject);
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    //Deleting a book
    // http://localhost:9090/api/books/1
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<HashMap> deleteBook(@PathVariable(value = "bookId") Long bookId) {
        System.out.println("calling deleteBook ==>");
        bookService.deleteBook(bookId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "book with id: " + bookId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    //Get Authors from Books
    // http://localhost:9090/api/books/1/authors
    @GetMapping("/books/{bookId}/authors")
    public List<Author> getBookAuthors(@PathVariable Long bookId){
        System.out.println("calling getBookAuthors ==>");
        return bookService.getBookAuthors(bookId);
    }

    //Add Book author in Books
    @PostMapping("/books/{bookId}/authors")
    public ResponseEntity<HashMap> createBookAuthor(@PathVariable(value = "bookId") Long bookId, @RequestBody Author authorObject) {
        System.out.println("calling createBookAutho ==>");
        bookService.createBookAuthor(bookId, authorObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "author with id: " + authorObject.getId() + " was successfully added.");
        responseMessage.put("result", bookService.getBookAuthors(bookId));
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    //Updating an Author

    //http://localhost:9090/api/books/1/authors/1
    @PutMapping("/books/{bookId}/authors/{authorId}")
    public ResponseEntity<HashMap> updateBookAuthor(@PathVariable Long bookId, @PathVariable Long authorId, @RequestBody Author authorObject){
        System.out.println("calling updateBookAuthor ==>");
        bookService.updateBookAuthor(bookId, authorId, authorObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "author with id: " + authorId + " was successfully updated.");
        responseMessage.put("result", bookService.getBookAuthors(bookId));
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    //Delete an Author
    //http://localhost:9090/api/books/1/authors/1
    @DeleteMapping("/books/{bookId}/authors/{authorId}")
    public ResponseEntity<HashMap> deleteBookAuthor(@PathVariable(value = "bookId") Long bookId, @PathVariable(value = "authorId") Long authorId) {
        System.out.println("calling deleteBookAuthor ==>");
        bookService.deleteBookAuthor(bookId, authorId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "author with id: " + authorId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    // Getting a list of genres for a book
    //http://localhost:9090/api/books/1/genres
    @GetMapping("/books/{bookId}/genres")
    public List<Genre> getBookGenre(@PathVariable Long bookId){
        System.out.println("calling getBookGenres ==>");
        return bookService.getBookGenres(bookId);
    }

    //Adding genre to a Book
    //http://localhost:9090/api/books/1/genres
    @PostMapping("/books/{bookId}/genres")
    public ResponseEntity<HashMap> createBookGenre(@PathVariable(value = "bookId") Long bookId, @RequestBody Genre genreObject) {
        System.out.println("calling createBookGenre ==>");
        bookService.createBookGenre(bookId, genreObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "genre with id: " + genreObject.getId() + " was successfully deleted.");
        responseMessage.put("result", bookService.getBookGenres(bookId));
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    //Updating a Genre for a Book
    //http://localhost:9090/api/books/1/genres/1
    @PutMapping("/books/{bookId}/genres/{genreId}")
    public ResponseEntity<HashMap> updateBookGenres(@PathVariable Long bookId, @PathVariable Long genreId, @RequestBody Genre genreObject){
        System.out.println("calling updateBookGenre ==>");
        bookService.updateBookGenres(bookId, genreId, genreObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "genre with id: " + genreId + " was successfully updated.");
        responseMessage.put("result", bookService.getBookGenres(bookId));
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    //Deleting a Genre in a Book
    //http://localhost:9090/api/books/1/genres/1
    @DeleteMapping("/books/{bookId}/genres/{genreId}")
    public ResponseEntity<HashMap> deleteBookGenre(@PathVariable(value = "bookId") Long bookId, @PathVariable(value = "genreId") Long genreId) {
        System.out.println("calling deleteBookGenre ==>");
        bookService.deleteBookGenre(bookId, genreId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "genre with id: " + genreId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    //Getting the Publishers in a Book
    //http://localhost:9090/api/books/1/publishers
    @GetMapping("/books/{bookId}/publishers")
    public List<Publisher> getBookPublishers(@PathVariable Long bookId){
        System.out.println("calling getBookPublishers ==>");
        return bookService.getBookPublishers(bookId);
    }

    //Adding a Publisher to a Book
    //http://localhost:9090/api/books/1/publishers
    @PostMapping("/books/{bookId}/publishers")
    public ResponseEntity<HashMap> createBookPublisher(@PathVariable(value = "bookId") Long bookId, @RequestBody Publisher publisherObject) {
        System.out.println("calling createBookPublisher ==>");
        bookService.createBookPublisher(bookId, publisherObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "publisher with id: " + publisherObject.getId() + " was successfully created.");
        responseMessage.put("result", bookService.getBookPublishers(bookId));
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }


    //Updating a Publisher for a Book
    //http://localhost:9090/api/books/1/publishers/1
    @PutMapping("/books/{bookId}/publishers/{publisherId}")
    public ResponseEntity<HashMap> updateBookPublisher(@PathVariable Long bookId, @PathVariable Long publisherId, @RequestBody Publisher publisherObject){
        System.out.println("calling updateBookGenre ==>");
        bookService.updateBookPublisher(bookId, publisherId, publisherObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "publisher with id: " + publisherId + " was successfully updated.");
        responseMessage.put("result", bookService.getBookPublishers(bookId));
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    //Deleting a Publisher in a Book
    //http://localhost:9090/api/books/1/publisher/1

    @DeleteMapping("/books/{bookId}/publishers/{publisherId}")
    public ResponseEntity<HashMap> deleteBookPublisher(@PathVariable(value = "bookId") Long bookId, @PathVariable(value = "publisherId") Long publisherId) {
        System.out.println("calling deleteBookPublisher ==>");
        bookService.deleteBookPublisher(bookId, publisherId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "publisher with id: " + publisherId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    //end
}




