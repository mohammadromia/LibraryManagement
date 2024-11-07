package com.managementsystem.library.controller;

import com.managementsystem.library.entities.Book;
import com.managementsystem.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public ResponseEntity<Object> saveBook(@Valid @RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable int id, @Valid @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }

    @GetMapping
    public ResponseEntity<Object> getAllBooks() {
        return bookService.getAllBook();
    }
}