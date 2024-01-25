package com.example.demo.book;

import com.example.demo.thirdparty.NYTimesClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;
    private final NYTimesClient nyTimesClient;
    private final static Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService, NYTimesClient nyTimesClient) {
        this.bookService = bookService;
        this.nyTimesClient = nyTimesClient;
    }

    @PostMapping
    Book addBook(@RequestBody Book book) {
        LOGGER.info("received a request to add a book ={}", book);
        Book retval = bookService.addBook(book);
        LOGGER.info("added a book successfully ={}", retval);
        return retval;
    }
    @GetMapping
    List<Book> getBooks(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("received a request to get all books");
        List<Book> retval = bookService.getBooks();
        LOGGER.info("books successfully retrieved = {}", retval);
        return retval;
    }

    @GetMapping("pagination/{offset}")
    Page<Book> getBooksWithPagination(@PathVariable("offset") int offset) {
        LOGGER.info("received a request to get books by pagination at offset = {}", offset);
        Page<Book> retval = bookService.getBooksWithPagination(offset);
        LOGGER.info("books successfully retrieved by pagination = {}", retval);
        return retval;
    }

    @PutMapping
    Book updateBook(@RequestBody Book book) {
        LOGGER.info("received a request to update book = {}", book);
        Book retval = bookService.updateBook(book);
        LOGGER.info("updated a book successfully = {}", retval);
        return retval;
    }

    @DeleteMapping("{id}")
    void deleteBookById(@PathVariable("id") Long id) {
        LOGGER.info("received a request to delete a book by id ={}", id);
        bookService.deleteBookById(id);
        LOGGER.info("book deleted successfully");
    }

    @GetMapping("/bestSeller/{category}")
    Object getBestSellers(@PathVariable("category") String category) {
        LOGGER.info("received a request to query bestSellers by category = {}", category);
        Object retval = nyTimesClient.getBestSellers(category);
        LOGGER.info("bestsellers queried successfully ={}", retval);
        return retval;
    }

}
