package com.example.demo.book;

import com.example.demo.constant.Constant;
import com.example.demo.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Book addBook(Book book) {
        book.setId(null);
        return bookRepo.save(book);
    }

    List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public Page<Book> getBooksWithPagination(int offset) {
        return bookRepo.findAll(PageRequest.of(offset, Constant.PAGE_SIZE));
    }

    public Book updateBook(Book book) {
        Optional<Book> optionalEntity = bookRepo.findById(book.getId());
        if (optionalEntity.isPresent()) {
            Book existingEntity = optionalEntity.get();
            existingEntity.setName(book.getName());
            return bookRepo.save(existingEntity);
        } else {
            throw new NotFoundException("book with ID" + book.getId() + " not found");
        }
    }

    void deleteBookById(Long id) {
        bookRepo.deleteById(id);
    }


}
