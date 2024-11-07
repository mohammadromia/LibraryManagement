package com.managementsystem.library.service;

import com.managementsystem.library.exceptions.ApiBaseException;
import com.managementsystem.library.entities.Book;
import com.managementsystem.library.repository.BookRepository;
import com.managementsystem.library.utils.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Cacheable(cacheNames = "Element",key = "#id")
    public ResponseEntity<Object> getBook(Integer id) {
        return bookRepository.findById(id)
                .map(book -> ResponseObject.SUCCESS_RESPONSE("Fetched Successfully", book))
                .orElse(ResponseObject.FAILED_RESPONSE("Entity Not Found", HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Object> update(Integer id, Book book) {
        return bookRepository.findById(id)
                .map(savedBook -> {
                    savedBook.setTitle(book.getTitle());
                    savedBook.setAuthor(book.getAuthor());
                    savedBook.setPublicationYear(book.getPublicationYear());
                    savedBook.setIsbn(book.getIsbn());
                    return bookRepository.save(savedBook);
                })
                .map(data -> ResponseObject.SUCCESS_RESPONSE("Fetched Successfully", null))
                .orElse(ResponseObject.FAILED_RESPONSE("Error updating the book", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    public ResponseEntity<Object> deleteBook(Integer id) {
        return bookRepository.findById(id)
                .map(book -> {
                    try {
                        bookRepository.delete(book);
                        return ResponseObject.SUCCESS_RESPONSE("Deleted Successfully", null);
                    } catch (Exception e) {
                        throw new ApiBaseException("Error deleting the entity", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                })
                .orElse(ResponseObject.FAILED_RESPONSE("Entity Not Found", HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Object> save(Book book) {

        try {
            return ResponseObject.SUCCESS_RESPONSE("Added Successfully", bookRepository.save(book));
        } catch (Exception e) {
            return ResponseObject.FAILED_RESPONSE("Error adding the book", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @Cacheable(cacheNames = "AllData",key = "getAll")
    public ResponseEntity<Object> getAllBook() {
        try {
            List<Book> books = bookRepository.findAll();
            return ResponseObject.SUCCESS_RESPONSE("Fetched Successfully", books);
        } catch (Exception e) {
            return ResponseObject.FAILED_RESPONSE("Error fetching books", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
