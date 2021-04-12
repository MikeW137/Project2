package com.bookstore.demo.controller;

import com.bookstore.demo.exception.InformationNotFoundException;
import com.bookstore.demo.model.Author;
import com.bookstore.demo.model.Book;
import com.bookstore.demo.repository.BookRepository;
import com.bookstore.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    //Create a book
    // http://localhost:9090/api/books/
    @PostMapping(path = "/books")
    public Book createBook(@RequestBody Book bookObject) {
        System.out.println("calling createBook ==>");
        return bookService.createBook(bookObject);
    }
    //Updating a book
    // http://localhost:9090/api/books/1
    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book bookObject) {
        System.out.println("calling updateBook ==>");
        return bookService.updateBook(bookId, bookObject);
    }

    //Deleting a book
    // http://localhost:9090/api/books/1
    @DeleteMapping("/books/{bookId}")
    public Optional<Book> deleteBook(@PathVariable(value = "bookId") Long bookId) {
        System.out.println("calling deleteCategory ==>");
        return bookService.deleteBook(bookId);
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
    public Author createBookAuthor(@PathVariable(value = "bookId") Long bookId, @RequestBody Author authorObject) {
        System.out.println("calling createCategoryRecipe ==>");
        return bookService.createBookAuthor(bookId, authorObject);
    }

    //Updating an Author

    //http://localhost:9090/api/books/1/authors/1
    @PutMapping("/books/{bookId}/authors/{authorId}")
    public Author updateBookAuthor(@PathVariable Long bookId, @PathVariable Long authorId, @RequestBody Author authorObject){
        System.out.println("calling updateBookAuthor ==>");
        return categoryService.updateCategoryRecipe(categoryId, recipeId, recipeObject);
    }
    //end
}




